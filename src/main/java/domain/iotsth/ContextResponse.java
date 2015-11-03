package domain.iotsth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Created by etheodor on 03/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContextResponse {
    ContextElement contextElement;
    StatusCode statusCode;

    public ContextResponse() {
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public ContextElement getContextElement() {
        return contextElement;
    }

    public void setContextElement(ContextElement contextElement) {
        this.contextElement = contextElement;
    }

    @Override
    public String toString() {
        return "ContextResponse{" +
                "contextElements=" +  contextElement +
                ", statusCode=" + statusCode +
                '}';
    }
}

