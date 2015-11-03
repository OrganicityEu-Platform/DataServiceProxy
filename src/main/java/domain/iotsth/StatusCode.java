package domain.iotsth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by etheodor on 03/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusCode {

    String code;
    String reasonPharse;

    public StatusCode() {
    }

    public StatusCode(String code, String reasonPharse) {
        this.code = code;
        this.reasonPharse = reasonPharse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReasonPharse() {
        return reasonPharse;
    }

    public void setReasonPharse(String reasonPharse) {
        this.reasonPharse = reasonPharse;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "code='" + code + '\'' +
                ", reasonPharse='" + reasonPharse + '\'' +
                '}';
    }
}
