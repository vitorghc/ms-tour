package com.example.kotlin.tour.configuration

import org.hibernate.validator.HibernateValidatorConfiguration
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import java.util.*
import javax.validation.ClockProvider
import javax.validation.Configuration

class CustomValidatorFactoryBean : LocalValidatorFactoryBean() {

    override fun getClockProvider(): ClockProvider? {
        return null
    }

    override fun postProcessConfiguration(configuration: Configuration<*>) {
        Optional.of(configuration)
            .filter { obj: Any? -> HibernateValidatorConfiguration::class.java.isInstance(obj) }
            .map { obj: Any? -> HibernateValidatorConfiguration::class.java.cast(obj) }
            .ifPresent { config -> config.propertyNodeNameProvider(JacksonPropertyNodeNameProvider()) }
        super.postProcessConfiguration(configuration)
    }
}