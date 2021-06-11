package com.example.kotlin.tour.configuration

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class MessageConfig : WebMvcConfigurer {

    @Bean(name = ["messageSource"])
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasenames("classpath:messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }

    @Bean
    fun validator(): LocalValidatorFactoryBean? {
        val customValidatorFactoryBean = CustomValidatorFactoryBean()
        customValidatorFactoryBean.setValidationMessageSource(messageSource())
        return customValidatorFactoryBean
    }

    override fun getValidator(): Validator? {
        return validator()
    }
}