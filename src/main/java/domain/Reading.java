package domain;

import java.util.HashMap;

/**
 * Created by etheodor on 28/08/2015.
 */
public class Reading {
    String uuid;
    Long timestamp;
    String measurement;
    String metric;
    HashMap<String, String> tags=null;

    public Reading(String uuid, Long timestamp, String measurement, String metric) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.measurement = measurement;
        this.metric = metric;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public HashMap<String, String> getTags() {
        return tags;
    }

    public void setTags(HashMap<String, String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Reading{" +
                "uuid='" + uuid + '\'' +
                ", timestamp=" + timestamp +
                ", measurement='" + measurement + '\'' +
                ", metric='" + metric + '\'' +
                ", tags=" + tags +
                '}';
    }
}
