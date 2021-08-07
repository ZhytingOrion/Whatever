package com.nic.calculate.help.sensitive;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;

public class SensitiveDataSerializer extends JsonSerializer<String> implements ContextualSerializer {
    private SensitiveType sensitiveType;

    public SensitiveDataSerializer() {
        this.sensitiveType = SensitiveType.DEFAULT;
    }

    public SensitiveDataSerializer(SensitiveType sensitiveType) {
        this.sensitiveType = sensitiveType;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(this.sensitiveType.insensitive().apply(s));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty == null) {
            return serializerProvider.findNullValueSerializer(beanProperty);
        }

        if (!beanProperty.getType().getRawClass().equals(String.class)) {
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }

        SensitiveField sensitiveConfig = beanProperty.getAnnotation(SensitiveField.class);
        if (sensitiveConfig == null) {
            sensitiveConfig = beanProperty.getContextAnnotation(SensitiveField.class);
        }
        if (sensitiveConfig == null) {
            return serializerProvider.findNullValueSerializer(beanProperty);
        }

        return new SensitiveDataSerializer(sensitiveConfig.value());
    }
}
