package com.this_rc.bookmicroservice.domain.util;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectConverterUtil {

    private static ModelMapper modelMapper = new ModelMapper();

    public static <S, D> D convert(final S source, Class<D> destinationClazz) {
        return modelMapper.map(source, destinationClazz);
    }

    public static <D, C> List<D> convertAll(final Collection<C> collection, Class<D> destinationClazz) {
        return collection.stream()
                .map(eachElementOfCollection -> convert(eachElementOfCollection, destinationClazz))
                .collect(Collectors.toList());
    }


}
