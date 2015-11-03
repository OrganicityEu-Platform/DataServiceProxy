package service;

import domain.iotsth.ContextElement;
import domain.iotsth.Response;
import domain.iotsth.Value;
import domain.smartcitizen.Reading;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    public ModelService() {
    }

    public domain.smartcitizen.Response getSmartCitizenResponse(Response santanderResponse, String from, String to, String function) throws Exception {
        domain.smartcitizen.Response response = new domain.smartcitizen.Response();
        if (santanderResponse.getContextResponses().length == 0) return response;
        ContextElement element = santanderResponse.getContextResponses()[0].getContextElement();
        response.setEntity_id(element.getId());
        if (element.getAttributes().length == 0) {
            return response;
        }
        response.setAttribute_id(element.getAttributes()[0].getName());
        response.setFrom(from);
        response.setTo(to);
        Value[] values = element.getAttributes()[0].getValues();
        Reading[] readings = new Reading[element.getAttributes()[0].getValues().length];
        int i = 0;
        for (Value v : values) {
            readings[i++]=new Reading(v.getRecvTime(),v.getAttrValue());
        }
        response.setReadings(readings);
        return response;
    }
}
