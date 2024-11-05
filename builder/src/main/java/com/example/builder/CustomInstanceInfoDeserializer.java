package com.example.builder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.netflix.appinfo.InstanceInfo;

import java.io.IOException;

public class CustomInstanceInfoDeserializer extends JsonDeserializer<InstanceInfo> {

    @Override
    public InstanceInfo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        // Custom deserialization logic here
        // For example, map the 'timestamp' field to the appropriate field in InstanceInfo
        // This is just an example, adjust according to your actual JSON structure
        long timestamp = node.get("timestamp").asLong();
        // Create and return an InstanceInfo object
        return InstanceInfo.Builder.newBuilder().setLastUpdatedTimestamp(timestamp).build();
    }
}