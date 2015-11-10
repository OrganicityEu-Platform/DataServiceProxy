package domain.iotsth;

/**
 * Created by etheodor on 10/11/2015.
 */
public class ValueId {
    String attrName;
    String origin;
    String resolution;

    public ValueId() {
    }

    public ValueId(String attrName, String origin, String resolution) {
        this.attrName = attrName;
        this.origin = origin;
        this.resolution = resolution;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "ValueId{" +
                "attrName='" + attrName + '\'' +
                ", origin='" + origin + '\'' +
                ", resolution='" + resolution + '\'' +
                '}';
    }
}
