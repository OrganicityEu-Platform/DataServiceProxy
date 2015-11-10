package domain.iotsth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by etheodor on 10/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Point {
    String offset;
    String samples;
    String sum;

    public Point() {
    }

    public Point(String offset, String samples, String value) {
        this.offset = offset;
        this.samples = samples;
        this.sum = value;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getSamples() {
        return samples;
    }

    public void setSamples(String samples) {
        this.samples = samples;
    }

    public String getValue() {
        Integer s=Integer.parseInt(samples);
        Double v=Double.parseDouble(sum);
        v=v/s;
        return v.toString();
    }

    public void setSum(String value) {
        this.sum = value;
    }



    @Override
    public String toString() {
        return "Point{" +
                "offset='" + offset + '\'' +
                ", samples='" + samples + '\'' +
                ", value='" + sum + '\'' +
                '}';
    }
}
