package domain;

import org.springframework.data.annotation.Id;

/**
 * Created by etheodor on 26/05/2016.
 */
public class ApiLogEntry {
    @Id
    private String id;

    String timestamp;
    String ip;
    String session;
    String service;
    String method;
    String asset;
    String attribute;
    String reguest;


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getReguest() {
        return reguest;
    }

    public void setReguest(String reguest) {
        this.reguest = reguest;
    }

    @Override
    public String toString() {
        return "ApiLogEntry{" +
                "timestamp='" + timestamp + '\'' +
                ", ip='" + ip + '\'' +
                ", session='" + session + '\'' +
                ", service='" + service + '\'' +
                ", method='" + method + '\'' +
                ", asset='" + asset + '\'' +
                ", attribute='" + attribute + '\'' +
                ", reguest='" + reguest + '\'' +
                '}';
    }
}
