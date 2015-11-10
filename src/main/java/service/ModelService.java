package service;

import domain.iotsth.ContextElement;
import domain.iotsth.Point;
import domain.iotsth.Response;
import domain.iotsth.Value;
import domain.smartcitizen.Reading;
import domain.smartphones.SmartphoneData;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ModelService {
    TimeZone tz = TimeZone.getTimeZone("UTC");
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    public ModelService() {
        df.setTimeZone(tz);
    }

    public domain.smartcitizen.Response getSmartCitizenResponse(Response santanderResponse, String from, String to, String function, String rollup) throws Exception {
        domain.smartcitizen.Response response = new domain.smartcitizen.Response();
        if (santanderResponse.getContextResponses().length == 0) return response;
        ContextElement element = santanderResponse.getContextResponses()[0].getContextElement();
        response.setEntity_id(element.getId().replace("_", ":"));
        if (element.getAttributes().length == 0) {
            return response;
        }
        response.setAttribute_id(element.getAttributes()[0].getName().replace("_", ":"));
        response.setFrom(from);
        response.setTo(to);
        response.setFunction(function);
        response.setRollup(rollup);
        Value[] values = element.getAttributes()[0].getValues();

        List<Reading> readings= new ArrayList<Reading>();
        int i = 0;
        for (Value v : values) {
            if (v.getPoints() != null && v.getPoints().length > 0) {
                String origin = v.get_id().getOrigin();
                 for (Point p : v.getPoints()) {
                    readings.add(new Reading(origin, p.getValue()));
                    break;
                }
            } else {
                readings.add(new Reading(v.getRecvTime(), v.getAttrValue()));
            }
        }
        Collections.sort(readings);
        Reading[] rA=new Reading[readings.size()];
        i=0;
        for(Reading r:readings){
            rA[i++]=r;
        }
        response.setReadings(rA );
        return response;
    }

    public domain.smartcitizen.Response getSmartCitizenResponse2(SmartphoneData smartphoneResponse, String from, String to, String function, String rollup) throws Exception {
        domain.smartcitizen.Response response = new domain.smartcitizen.Response();
        response.setEntity_id(smartphoneResponse.getEntity_id());
        response.setAttribute_id(smartphoneResponse.getAttribute_id());
        response.setFrom(from);
        response.setTo(to);
        response.setFunction(function);
        response.setRollup(rollup);
        List<List<Object>> values = smartphoneResponse.getReadings();
        Reading[] readings = new Reading[values.size()];
        int i = 0;
        for (List<Object> v : values) {
            readings[i++] = new Reading(v.get(0).toString(), v.get(1).toString());
        }
        response.setReadings(readings);
        return response;
    }
}
