package com.Davivienda.utils;

import com.Davivienda.model.TipoTransaccion;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class TipoTransaccionConverter implements AttributeConverter<TipoTransaccion, String> {

@Override
public String convertToDatabaseColumn(TipoTransaccion tipo) {
	return tipo != null ? tipo.name() : null;
}

@Override
public TipoTransaccion convertToEntityAttribute(String dbData) {
	return dbData != null ? TipoTransaccion.valueOf(dbData) : null;
}
}