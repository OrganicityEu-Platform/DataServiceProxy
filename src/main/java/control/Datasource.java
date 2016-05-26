package control;

import domain.ApiLogEntry;
import domain.smartcitizen.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@RestController
public class Datasource {


    @Autowired
    SantanderAPIService santanderAPIService;

    @Autowired
    SmartphoneAPIService smartphoneAPIService;

    @Autowired
    PatraAPIService patraAPIService;

    @Autowired
    LondonAPIService londonAPIService;

    @Autowired
    ModelService modelService;

    @Autowired
    ApiLogRepository apiLogRepository;

    @Cacheable(value = "entitiesCache", key = "{ #uuid,#attribute_id, #from, #to, #function, #rollup, #limit, #offset }")
    @RequestMapping(value = "api/v1/entities/{uuid}/readings", method = RequestMethod.GET)
    public Response datasource(@PathVariable(value = "uuid") String uuid, @RequestParam(value = "attribute_id") String attribute_id,
                               @RequestParam(value = "from") String start, @RequestParam(value = "to") String end,
                               @RequestParam(value = "function", required = false) String function,
                               @RequestParam(value = "rollup", required = false) String rollup, //m, h , d
                               @RequestParam(value = "limit", required = false) String limit,
                               @RequestParam(value = "offset", required = false) String offset,
                               HttpServletResponse response,
                               HttpServletRequest request) throws Exception {

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        df.setTimeZone(tz);
        ApiLogEntry ale = new ApiLogEntry();
        ale.setTimestamp(df.format(new Date()));
        ale.setSession(request.getSession().getId());
        ale.setService("datasource");
        ale.setMethod("entities");
        ale.setIp(request.getRemoteAddr());
        ale.setAsset(uuid);
        ale.setAttribute(attribute_id);

        apiLogRepository.save(ale);
        Response r = null;
        try {
            if (uuid.startsWith("urn:oc:entity:santander") == true) {
                try {
                    uuid = uuid.replace(":", "_");
                    //attribute_id = attribute_id.replace(":", "_");
                    if (attribute_id.startsWith("urn:oc:attributeType:") == true) {
                        attribute_id = attribute_id.replace("urn:oc:attributeType:", "");
                    }
                    if (attribute_id.contains("Bike")) {
                        r = modelService.getSmartCitizenResponse(santanderAPIService.getData(uuid, "urn_oc_entityType_bikeStation", attribute_id, start, end, rollup, function, offset, limit), start, end, function, rollup);
                    } else {
                        r = modelService.getSmartCitizenResponse(santanderAPIService.getData(uuid, "urn_oc_entityType_iotdevice", attribute_id, start, end, rollup, function, offset, limit), start, end, function, rollup);
                    }
                } catch (Exception e) {
                    throw e;
                }
            } else if (uuid.startsWith("urn:oc:entity:london:smartphone") == true) {
                try {
                    r = modelService.getSmartCitizenResponse2(smartphoneAPIService.getData(uuid, attribute_id, start, end, rollup, function, offset, limit), start, end, function, rollup);
                } catch (Exception e) {
                    throw e;
                }
            } else if (uuid.startsWith("urn:oc:entity:patra:smartphone") == true) {
                try {
                    r = modelService.getSmartCitizenResponse2(smartphoneAPIService.getData(uuid, attribute_id, start, end, rollup, function, offset, limit), start, end, function, rollup);
                } catch (Exception e) {
                    throw e;
                }
            } else if (uuid.startsWith("urn:oc:entity:patra") == true) {
                try {
                    r = modelService.getSmartCitizenResponse2(patraAPIService.getData(uuid, attribute_id, start, end, rollup, function, offset, limit), start, end, function, rollup);
                } catch (Exception e) {
                    throw e;
                }
            } else if (uuid.startsWith("urn:oc:entity:london:") == true) {
                try {

                    r = londonAPIService.getData(uuid, attribute_id, start, end, rollup, function, offset, limit);
                } catch (Exception e) {
                    throw e;
                }
            }
            if (r.getReadings() == null || r.getReadings().length == 0) {
                response.sendError(HttpStatus.NOT_FOUND.value());
                return null;
            }
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value());
            return null;
        }
        return r;
    }


}