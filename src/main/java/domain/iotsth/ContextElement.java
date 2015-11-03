package domain.iotsth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Created by etheodor on 03/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContextElement {

    Attribute[] attributes;
    String id;
    String isPattern;
    String type;

    public ContextElement() {
    }

    public ContextElement(String id, String isPattern, String type) {
        this.id = id;
        this.isPattern = isPattern;
        this.type = type;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsPattern() {
        return isPattern;
    }

    public void setIsPattern(String isPattern) {
        this.isPattern = isPattern;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ContextElement{" +
                "attributes=" + Arrays.toString(attributes) +
                ", id='" + id + '\'' +
                ", isPattern='" + isPattern + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
