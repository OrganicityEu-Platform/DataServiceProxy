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
    public Response getData(@PathVariable(value = "uuid") String uuid, @RequestParam(value = "attribute_id") String attribute_id, @RequestParam(value = "from") String start, @RequestParam(value = "to") String end, @RequestParam(value = "function") String function) throws Exception {
        try {
            return modelService.getSmartCitizenResponse(santanderAPIService.getData(uuid, "urn_oc_entityType_iotdevice", attribute_id, start, end, null, null),start,end,"");

        } catch (Exception e) {
            throw e;
        }
    }


}