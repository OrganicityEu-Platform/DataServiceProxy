package domain.iotsth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by etheodor on 03/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {
    String recvTime;
    String attrType;
    String attrValue;

    public Value() {
    }

    public Value(String recvTime, String attrType, String attrValue) {
        this.recvTime = recvTime;
        this.attrType = attrType;
        this.attrValue = attrValue;
    }

    public String getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(String recvTime) {
        this.recvTime = recvTime;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }


    @Override
    public String toString() {
        return "Value{" +
                "recvTime='" + recvTime + '\'' +
                ", attrType='" + attrType + '\'' +
                ", attrValue='" + attrValue + '\'' +
                '}';
    }
}
