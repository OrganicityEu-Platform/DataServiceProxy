package service;

import domain.iotsth.ContextElement;
import domain.iotsth.Response;
import domain.iotsth.Value;
import domain.smartcitizen.Reading;
import domain.smartphones.SmartphoneData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    public ModelService() {
    }

    public domain.smartcitizen.Response getSmartCitizenResponse(Response santanderResponse, String from, String to, String function) throws Exception {
        domain.smartcitizen.Response response = new domain.smartcitizen.Response();
        if (santanderResponse.getContextResponses().length == 0) return response;
        ContextElement element = santanderResponse.getContextResponses()[0].getContextElement();
        response.setEntity_id(element.getId().replace("_",":"));
        if (element.getAttributes().length == 0) {
            return response;
        }
        response.setAttribute_id(element.getAttributes()[0].getName().replace("_",":"));
        response.setFrom(from);
        response.setTo(to);
        Value[] values = element.getAttributes()[0].getValues();
        Reading[] readings = new Reading[element.getAttributes()[0].getValues().length];
        int i = 0;
        for (Value v : values) {
            readings[i++] = new Reading(v.getRecvTime(), v.getAttrValue());
        }
        response.setReadings(readings);
        return response;
    }

    public domain.smartcitizen.Response getSmartCitizenResponse2(SmartphoneData smartphoneResponse) throws Exception {
        domain.smartcitizen.Response response = new domain.smartcitizen.Response();
        response.setAttribute_id(smartphoneResponse.getAttribute_id());
        response.setFrom(smartphoneResponse.getFrom());
        response.setTo(smartphoneResponse.getTo());
        response.setFrom(smartphoneResponse.getFunction());
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
