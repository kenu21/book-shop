package com.keniu.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.NullValueCheckStrategy;

/**
 * Global configuration for MapStruct mappers in the application.
 * This configuration sets the mapping behavior for dependency injection,
 * null value checks, and implementation package naming.
 */
@org.mapstruct.MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.IMPL"
)
public class MapperConfig {
}
