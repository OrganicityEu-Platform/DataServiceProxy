package service;

import config.SmartphoneConfiguration;
import domain.iotsth.Response;
import domain.smartphones.SmartphoneData;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class SmartphoneAPIService {
    // call example
    // http://195.220.224.231:8080/api/v1/devices/urn:oc:entity:london:smartphone:phone:2/readings?attribute_id=urn:oc:atributeType:soundPressureLevel:ambient&from=2015-10-01T00:57Z&to=2015-12-02T11:57Z&function=avg

    @Autowired
    SmartphoneConfiguration smartphoneConfiguration;

    public SmartphoneAPIService() {
    }

    public SmartphoneAPIService(SmartphoneConfiguration ac) {
        this.smartphoneConfiguration = ac;
    }

    public static class CustomResponseErrorHandler implements ResponseErrorHandler {
        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            boolean hasError = false;
            int rawStatusCode = response.getRawStatusCode();
            if (rawStatusCode != 200) {
                hasError = true;
            }
            return hasError;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            if (response.getRawStatusCode() != 204)
                throw new IOException("AB Error:" + response.getRawStatusCode() + ":" + response.getStatusText() + ":" + getStringFromInputStream(response.getBody()));
            else
                throw new IOException("AB Error:" + response.getRawStatusCode() + ":" + response.getStatusText());
        }

        private static String getStringFromInputStream(InputStream is) {
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sb.toString();
        }
    }


    public SmartphoneData getData(String entity_id,   String attribute_id, String start, String end, String offset, String limit, String function) throws Exception {
        RestTemplate client = new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));
        client.setErrorHandler(new CustomResponseErrorHandler());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        messageConverters.add(new GsonHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        client.setMessageConverters(messageConverters);

        HttpHeaders requestHeaders = new HttpHeaders();
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.ALL);
        requestHeaders.setAccept(acceptableMediaTypes);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        String url = smartphoneConfiguration.getSensorDataEndpoint() + "/" + entity_id + "/readings?attribute_id=" + attribute_id;
        url += "&from=" + start;
        url += "&to=" + end;
        if (limit != null)
            url += "&hLimit=" + limit;
        else
            url += "&hLimit=" + 1000;
        if (offset != null)
            url += "&hOffset=" + offset;
        else
            url += "&hOffset=" + 0;
        if (function != null)
            url += "&function=" + function;
        else
            url += "&function=avg";

        try {
            ResponseEntity<SmartphoneData> response = client.exchange(url, HttpMethod.GET, requestEntity, SmartphoneData.class);
            if (response.getBody().toString().contains("error")) {
                throw new Exception(response.getBody().toString());
            }
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
