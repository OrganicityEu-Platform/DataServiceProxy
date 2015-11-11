package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by etheodor on 20/08/2015.
 */
@Component
public class LondonConfiguration {

    private String username;
    private String password;
    private String endpoint;

    @Autowired
    public LondonConfiguration(@Value("${LondonUsername}") String prop1, @Value("${LondonPassword}") String prop2, @Value("${LondonEndpoint}") String prop3) {
        this.username = prop1;
        this.password = prop2;
        this.endpoint=prop3;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDeviceEndpoint() {
        return endpoint;
    }

    public String getSensorEndpoint() {
        return endpoint;
    }

    public String getSensorDataEndpoint() {
        return endpoint;
    }
}