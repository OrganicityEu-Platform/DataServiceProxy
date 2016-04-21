import config.SmartphoneConfiguration;
import domain.smartphones.SmartphoneData;
import org.testng.annotations.Test;
import service.ModelService;
import service.SmartphoneAPIService;

/**
 * Created by etheodor on 19/08/2015.
 */
@Test
public class SmartphoneAPITest {
    private static String endpoint = "http://195.220.224.231:8080/api/v1/devices";
    private static String username = "";
    private static String password = "";
    private static String uuid = "urn:oc:entity:london:smartphone:phone:2";
    private static String sensor = "urn:oc:atributeType:soundPressureLevel:ambient";
    private static String start = "2014-10-01T00:00Z";
    private static String end = "2015-12-02T11:57Z";

    @org.testng.annotations.Test
    public void testGetSensorData() throws Exception {
        SmartphoneConfiguration ac = new SmartphoneConfiguration(username, password, endpoint);
      //  SmartphoneData data = (new SmartphoneAPIService(ac)).getData(uuid, sensor, start, end, "0", "10","avg");
        //System.out.println(data.toString());
       // domain.smartcitizen.Response r=(new ModelService()).getSmartCitizenResponse2(data);
        //System.out.println(r.toString());
    }
}