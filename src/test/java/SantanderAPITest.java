import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.SantanderConfiguration;
import domain.iotsth.Response;
import org.testng.annotations.Test;
import service.SantanderAPIService;

/**
 * Created by etheodor on 19/08/2015.
 */
@Test
public class SantanderAPITest {
    private static String endpoint = "http://mu.tlmat.unican.es:8098/STH/v1/contextEntities/type";
    private static String username = "";
    private static String password = "";
    private static String uuid = "urn_oc_entity_santander_environmental_t93";
    private static String sensor = "temperature";
    private static String start = "2015-10-14T00:00:00.000Z";
    private static String end = "2015-11-15T23:59:59.999Z";
    String x = "{\"contextResponses\":[{\"contextElement\":{\"attributes\":[{\"name\":\"temperature\",\"values\":[{\"recvTime\":\"2015-10-29T15:21:02.000Z\",\"attrType\":\"urn:oc:attributeType:temperature:ambient\",\"attrValue\":\"40.2344,-3.87768\"},{\"recvTime\":\"2015-10-29T15:21:11.000Z\",\"attrType\":\"urn:oc:attributeType:temperature:ambient\",\"attrValue\":\"40.2344,-3.87768\"},{\"recvTime\":\"2015-10-29T15:21:21.000Z\",\"attrType\":\"urn:oc:attributeType:temperature:ambient\",\"attrValue\":\"40.2344,-3.87768\"},{\"recvTime\":\"2015-10-29T15:21:31.000Z\",\"attrType\":\"urn:oc:attributeType:temperature:ambient\",\"attrValue\":\"40.2344,-3.87768\"},{\"recvTime\":\"2015-10-29T15:21:41.000Z\",\"attrType\":\"urn:oc:attributeType:temperature:ambient\",\"attrValue\":\"40.2344,-3.87768\"},{\"recvTime\":\"2015-10-29T15:21:51.000Z\",\"attrType\":\"urn:oc:attributeType:temperature:ambient\",\"attrValue\":\"40.2344,-3.87768\"},{\"recvTime\":\"2015-10-29T15:22:01.000Z\",\"attrType\":\"urn:oc:attributeType:temperature:ambient\",\"attrValue\":\"40.2344,-3.87768\"},{\"recvTime\":\"2015-10-29T15:22:11.000Z\",\"attrType\":\"urn:oc:attributeType:temperature:ambient\",\"attrValue\":\"40.2344,-3.87768\"},{\"recvTime\":\"2015-10-29T15:22:21.000Z\",\"attrType\":\"urn:oc:attributeType:temperature:ambient\",\"attrValue\":\"40.2344,-3.87768\"},{\"recvTime\":\"2015-10-29T15:22:31.000Z\",\"attrType\":\"urn:oc:attributeType:temperature:ambient\",\"attrValue\":\"40.2344,-3.87768\"}]}],\"id\":\"urn_oc_entity_santander_environmental_t93\",\"isPattern\":false,\"type\":\"urn_oc_entityType_iotdevice\"},\"statusCode\":{\"code\":\"200\",\"reasonPhrase\":\"OK\"}}]}";

    @org.testng.annotations.Test
    public void testGetSensorData() throws Exception {
        SantanderConfiguration ac = new SantanderConfiguration(username, password, endpoint);
        Response data = (new SantanderAPIService(ac)).getData(uuid, "urn_oc_entityType_iotdevice", sensor, start, end, "0", "10");
            System.out.println(data.toString());
    }
}