package control;

import domain.smartcitizen.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ModelService;
import service.SantanderAPIService;
import service.SmartphoneAPIService;

@RestController
public class Controller {


    @Autowired
    SantanderAPIService santanderAPIService;

    @Autowired
    SmartphoneAPIService smartphoneAPIService;

    @Autowired
    ModelService modelService;

    @RequestMapping("api/v1/entities/{uuid}/readings")
    public Response getData(@PathVariable(value = "uuid") String uuid, @RequestParam(value = "attribute_id") String attribute_id,
                            @RequestParam(value = "from") String start, @RequestParam(value = "to") String end,
                            @RequestParam(value = "function", required = false) String function,
                            @RequestParam(value = "rollup", required = false) String rollup, //m, h , d
                            @RequestParam(value = "limit", required = false) String limit,
                            @RequestParam(value = "offset", required = false) String offset) throws Exception {
        if (uuid.startsWith("urn:oc:entity:santander") == true) {
            try {
                uuid = uuid.replace(":", "_");
                //attribute_id = attribute_id.replace(":", "_");
                return modelService.getSmartCitizenResponse(santanderAPIService.getData(uuid, "urn_oc_entityType_iotdevice", attribute_id, start, end, rollup, function, offset, limit), start, end, function,rollup);
            } catch (Exception e) {
                throw e;
            }
        } else if (uuid.startsWith("urn:oc:entity:london:smartphone") == true) {
            try {

                return modelService.getSmartCitizenResponse2(smartphoneAPIService.getData(uuid, attribute_id, start, end, rollup, function, offset, limit), start, end, function, rollup);
            } catch (Exception e) {
                throw e;
            }
        }

        return null;
    }


}