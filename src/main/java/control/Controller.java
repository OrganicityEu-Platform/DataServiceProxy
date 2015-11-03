package control;

import domain.smartcitizen.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ModelService;
import service.SantanderAPIService;

@RestController
public class Controller {


    @Autowired
    SantanderAPIService santanderAPIService;
    @Autowired
    ModelService modelService;

    @RequestMapping("api/v1/devices/{uuid}/readings")
    public Response getData(@PathVariable(value = "uuid") String uuid, @RequestParam(value = "attribute_id") String attribute_id, @RequestParam(value = "from") String start, @RequestParam(value = "to") String end, @RequestParam(value = "function") String function, @RequestParam(value = "group") String group, @RequestParam(value = "limit") String limit, @RequestParam(value = "offset") String offset) throws Exception {
        if (uuid.startsWith("urn:oc:entity:santander") == true) {
            try {
                uuid = uuid.replace(":", "_");
                attribute_id = attribute_id.replace(":", "_");
                return modelService.getSmartCitizenResponse(santanderAPIService.getData(uuid, "urn_oc_entityType_iotdevice", attribute_id, start, end, null, null), start, end, "");
            } catch (Exception e) {
                throw e;
            }
        } else if (uuid.startsWith("urn:oc:entity:london:smartphone") == true){

        }

        return null;
    }


}