package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by amaxilat on 2/12/2015.
 */
@Component
public class PatraConfiguration {

    private String username;
    private String password;
    private String endpoint;

    @Autowired
    public PatraConfiguration(@Value("${PatraUsername}") String prop1, @Value("${PatraPassword}") String prop2, @Value("${PatraEndpoint}") String prop3) {
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