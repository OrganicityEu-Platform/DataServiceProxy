package domain.smartcitizen;

import java.util.Arrays;

/**
 * Created by etheodor on 28/08/2015.
 */
public class Response {

    String entity_id;
    String attribute_id;
    String from;
    String to;
    String function;
    String rollup;
    Reading[] readings;

    public Response() {
    }



    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(String attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Reading[] getReadings() {
        return readings;
    }

    public void setReadings(Reading[] readings) {
        this.readings = readings;
    }

    public Response(String entity_id, String attribute_id, String from, String to, String function, String rollup, Reading[] readings) {
        this.entity_id = entity_id;
        this.attribute_id = attribute_id;
        this.from = from;
        this.to = to;
        this.function = function;
        this.rollup = rollup;
        this.readings = readings;
    }

    public String getRollup() {
        return rollup;
    }

    public void setRollup(String rollup) {
        this.rollup = rollup;
    }

    @Override
    public String toString() {
        return "Response{" +
                "entity_id='" + entity_id + '\'' +
                ", attribute_id='" + attribute_id + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", function='" + function + '\'' +
                ", rollup='" + rollup + '\'' +
                ", readings=" + Arrays.toString(readings) +
                '}';
    }
}
