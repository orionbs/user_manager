package fr.orionbs.user_manager.adapter.exception.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class InternationalizationConfig {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public MessageSource validatorMessageSource() {
        ReloadableResourceBundleMessageSource validatorMessageSource = new ReloadableResourceBundleMessageSource();
        validatorMessageSource.setBasename("classpath:static/language/validators");
        validatorMessageSource.setDefaultEncoding("UTF-8");
        return validatorMessageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(MessageSource validatorMessageSource) {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(validatorMessageSource);
        return localValidatorFactoryBean;
    }

    @Bean
    public MessageSource exceptionMessageSource() {
        ReloadableResourceBundleMessageSource exceptionMessageSource = new ReloadableResourceBundleMessageSource();
        exceptionMessageSource.setBasename("classpath:static/language/exceptions");
        exceptionMessageSource.setDefaultEncoding("UTF-8");
        return exceptionMessageSource;
    }

}
