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
    private static String uuid = "urn_oc_entity_santander_environmental_t94";
    private static String sensor = "temperature";
    private static String start = "2015-10-14T00:00:00.000Z";
    private static String end = "2015-11-15T23:59:59.999Z";

    @org.testng.annotations.Test
    public void testGetSensorData() throws Exception {
        SantanderConfiguration ac = new SantanderConfiguration(username, password, endpoint);
        Response data = (new SantanderAPIService(ac)).getData(uuid, "urn_oc_entityType_iotdevice", sensor, start, end, "hour","sum", "0", "100");
            System.out.println(data.toString());
    }
}