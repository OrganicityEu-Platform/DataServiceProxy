package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.SantanderConfiguration;
import domain.iotsth.ContextResponse;
import domain.iotsth.Response;
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
public class SantanderAPIService {
    // call example
    // http://mu.tlmat.unican.es:8098/STH/v1/contextEntities/type/urn_oc_entityType_iotdevice/id/urn_oc_entity_santander_environmental_t93/attributes/temperature?hLimit=3&hOffset=0&dateFrom=2014-02-14T00:00:00.000Z&dateTo=2015-10-29T23:59:59.999Z

    @Autowired
    SantanderConfiguration santanderConfiguration;

    public SantanderAPIService() {
    }

    public SantanderAPIService(SantanderConfiguration ac) {
        this.santanderConfiguration = ac;
    }

    private static Gson gSon = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.000'Z'").create();


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


    public Response getData(String entity_id, String entity_type, String attribute_id, String start, String end, String aggregationPeriod, String func, String offset, String limit) throws Exception {
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
        requestHeaders.set("Fiware-Service", "ocservice");
        requestHeaders.set("Fiware-ServicePath", "/ocservice_servpath");
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);


        String url = santanderConfiguration.getSensorDataEndpoint() + "/" + entity_type + "/id/" + entity_id + "/attributes/" + attribute_id;
        url += "?dateFrom=" + start;
        url += "&dateTo=" + end;

        if (aggregationPeriod != null) {
            url += "&aggrPeriod=" + aggregationPeriod;
        }
        if (func != null)
            url += "&aggrMethod=" + func;

        if (aggregationPeriod == null) {
            if (limit != null)
                url += "&hLimit=" + limit;
            else
                url += "&hLimit=" + 1000;
            if (offset != null)
                url += "&hOffset=" + offset;
            else
                url += "&hOffset=" + 0;
        }


        try {
            ResponseEntity<Response> response = client.exchange(url, HttpMethod.GET, requestEntity, Response.class);
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
