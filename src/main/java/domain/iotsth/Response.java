package domain.iotsth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Created by etheodor on 03/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    ContextResponse[] contextResponses;

    public Response() {
    }

    public ContextResponse[] getContextResponses() {
        return contextResponses;
    }

    public void setContextResponses(ContextResponse[] contextResponses) {
        this.contextResponses = contextResponses;
    }

    @Override
    public String toString() {
        return "Response{" +
                "contextResponses=" + Arrays.toString(contextResponses) +
                '}';
    }
}
