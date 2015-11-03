package domain.smartcitizen;

import java.util.HashMap;

/**
 * Created by etheodor on 28/08/2015.
 */
public class Reading {

    String datetime;
    String value;

    public Reading(String datetime, String value) {
        this.datetime = datetime;
        this.value = value;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Reading{" +
                "datetime='" + datetime + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
