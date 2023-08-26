package br.com.power.sense.service.converters;

import br.com.power.sense.model.enums.SexoEnum;
import org.springframework.core.convert.converter.Converter;

public class StringToSexoEnumConverter implements Converter<String, SexoEnum> {
    @Override
    public SexoEnum convert(String source) {
        try {
            return SexoEnum.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
