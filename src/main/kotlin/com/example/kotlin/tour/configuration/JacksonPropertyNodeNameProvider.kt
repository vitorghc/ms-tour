package com.example.kotlin.tour.configuration

import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition
import org.hibernate.validator.spi.nodenameprovider.JavaBeanProperty
import org.hibernate.validator.spi.nodenameprovider.Property
import org.hibernate.validator.spi.nodenameprovider.PropertyNodeNameProvider


class JacksonPropertyNodeNameProvider : PropertyNodeNameProvider {

    private val objectMapper = ObjectMapper()

    override fun getName(property: Property): String? {
        return if (property is JavaBeanProperty) {
            getJavaBeanPropertyName(property as JavaBeanProperty)
        } else getDefaultName(property)
    }

    private fun getJavaBeanPropertyName(property: JavaBeanProperty): String? {
        val type: JavaType = objectMapper.constructType(property.declaringClass)
        val desc: BeanDescription = objectMapper.serializationConfig.introspect(type)
        return desc.findProperties()
            .stream()
            .filter { prop -> prop.internalName.equals(property.name) }
            .map(BeanPropertyDefinition::getName)
            .findFirst()
            .orElse(property.name)
    }

    private fun getDefaultName(property: Property): String? {
        return property.name
    }

}