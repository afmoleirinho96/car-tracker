package com.automotive.tracker.model.annotations;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.METHOD,
    ElementType.TYPE
})
@Qualifier
public @interface VinDecoderApi {
}
