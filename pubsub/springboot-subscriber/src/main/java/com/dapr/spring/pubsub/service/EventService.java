package com.dapr.spring.pubsub.service;

import com.dapr.spring.pubsub.pojo.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.data.PojoCloudEventData;
import io.cloudevents.core.format.EventFormat;
import io.cloudevents.core.provider.EventFormatProvider;
import io.cloudevents.jackson.JsonFormat;
import io.cloudevents.jackson.PojoCloudEventDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.cloudevents.core.CloudEventUtils.mapData;

@Service
public class EventService {

    private ObjectMapper objectMapper;

    @Autowired
    public EventService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Message getEventMessage(CloudEvent event) {
        PojoCloudEventData<Message> cloudEventData = mapData(
                event,
                PojoCloudEventDataMapper.from(objectMapper, Message.class)
        );
        return cloudEventData.getValue();
    }

    public CloudEvent getCloudEvent(byte[] body) {
        EventFormat format = EventFormatProvider
                .getInstance()
                .resolveFormat(JsonFormat.CONTENT_TYPE);
        return format.deserialize(body);
    }

}
