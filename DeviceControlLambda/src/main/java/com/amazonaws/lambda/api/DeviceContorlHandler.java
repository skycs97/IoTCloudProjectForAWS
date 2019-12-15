package com.amazonaws.lambda.api;

import java.nio.ByteBuffer;
import com.amazonaws.services.iotdata.AWSIotData;
import com.amazonaws.services.iotdata.AWSIotDataClientBuilder;
import com.amazonaws.services.iotdata.model.UpdateThingShadowRequest;
import com.amazonaws.services.iotdata.model.UpdateThingShadowResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.annotation.JsonCreator;
/*
 * 디바이스의 상태를 변경하는 Lambda
 * 입력은 디바이스이름과 
 * tag안에 이름과 값의 쌍으로 들어옴
 */
public class DeviceContorlHandler implements RequestHandler<Event, String> {

    @Override
    public String handleRequest(Event event, Context context) {
        context.getLogger().log("Input: " + event);

        AWSIotData iotData = AWSIotDataClientBuilder.standard().build();

        String payload = getPayload(event.tag);

        //update 를 이용하여 payload값을 보냄
        UpdateThingShadowRequest updateThingShadowRequest  = 
                new UpdateThingShadowRequest()
                    .withThingName(event.device)
                    .withPayload(ByteBuffer.wrap(payload.getBytes()));
        //결과 확인
        UpdateThingShadowResult result = iotData.updateThingShadow(updateThingShadowRequest);
        byte[] bytes = new byte[result.getPayload().remaining()];
        result.getPayload().get(bytes);
        String resultString = new String(bytes);
        return resultString;
    }

    private String getPayload(Tag tag) {
    	//tag의 값을 바탕으로 변경 요청을 위한 json형태로 데이터 전처리
        String tagstr = String.format("\"%s\" : \"%s\"", tag.tagName, tag.tagValue);

        return String.format("{ \"state\": { \"desired\": { %s } } }", tagstr);
    }

}
//입력 데이터의 json을 클래스 데이터로 역직렬화 하기 위한 형태
class Event {
    public String device;
    public Tag tag;
    
    public Event() {
    	tag = new Tag();
    }
}

class Tag {
    public String tagName;
    public String tagValue;

    @JsonCreator 
    public Tag() {
    }

    public Tag(String n, String v) {
        tagName = n;
        tagValue = v;
    }
}
