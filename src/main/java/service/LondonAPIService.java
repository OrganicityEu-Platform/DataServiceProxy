package service;

import config.LondonConfiguration;
import config.SmartphoneConfiguration;
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
public class LondonAPIService {

    @Autowired
    LondonConfiguration londonConfiguration;

    public LondonAPIService() {
    }

    public LondonAPIService(LondonConfiguration ac) {
        this.londonConfiguration = ac;
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


    public domain.smartcitizen.Response getData(String entity_id, String attribute_id, String start, String end, String rollup, String function, String offset, String limit) throws Exception {
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
        String url = londonConfiguration.getSensorDataEndpoint() + "/" + entity_id + "/readings?attribute_id=" + attribute_id;
        url += "&from=" + start;
        url += "&to=" + end;
        if (limit != null)
            url += "&limit=" + limit;
        else
            url += "&limit=" + 1000;
        if (offset != null)
            url += "&offset=" + offset;
        else
            url += "&offset=" + 0;
        if (function != null)
            url += "&function=" + function;
        else
            url += "&function=avg";
        if (rollup != null)
            url += "&rollup=" + rollup;


        try {
            ResponseEntity<domain.smartcitizen.Response> response = client.exchange(url, HttpMethod.GET, requestEntity, domain.smartcitizen.Response.class);
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
