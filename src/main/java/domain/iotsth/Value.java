package domain.iotsth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

/**
 * Created by etheodor on 03/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {
    String recvTime;
    String attrType;
    String attrValue;
    ValueId _id;
    Point[] points;

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

    public ValueId get_id() {
        return _id;
    }

    public void set_id(ValueId _id) {
        this._id = _id;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Value{" +
                "recvTime='" + recvTime + '\'' +
                ", attrType='" + attrType + '\'' +
                ", attrValue='" + attrValue + '\'' +
                ", points=" + ((points !=null)? Arrays.toString(points) : "") +
                ", _id=" +((_id !=null) ?_id.toString():"") +
                '}';
    }
}
