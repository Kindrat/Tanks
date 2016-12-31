package com.github.kindrat.programmerwars.server.conversion;

import lombok.SneakyThrows;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ConverterFactory {
    private Map<Class<?>, Map<Class<?>, Converter<?, ?>>> converters;

    public ConverterFactory() {
        converters = new ConcurrentHashMap<>();
    }

    public void addConverter(Converter<?, ?> converter) {
        ResolvableType type = ResolvableType.forType(converter.getClass().getGenericInterfaces()[0]);
        ResolvableType sourceType = type.getGeneric(0);
        ResolvableType targetType = type.getGeneric(1);

        converters.computeIfAbsent(sourceType.getRawClass(), aClass -> new ConcurrentHashMap<>())
                .computeIfAbsent(targetType.getRawClass(), aClass -> converter);
    }

    @SneakyThrows
    public <S, T> T convert(S source, Class<T> target) {
        Map<Class<?>, Converter<?, ?>> map = Optional.ofNullable(converters.get(source.getClass()))
                .orElseThrow(RuntimeException::new);
        @SuppressWarnings("unchecked")
        Converter<S, T> converter = (Converter<S, T>) Optional.ofNullable(map.get(target))
                .orElseThrow(RuntimeException::new);
        return converter.convert(source);
    }

    public <S, T> List<T> convert(Collection<S> source, Class<T> target) {
        return source.stream().map(s -> convert(s, target)).collect(Collectors.toList());
    }
}
