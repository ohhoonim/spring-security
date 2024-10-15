package dev.ohhoonim.spring_security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("{value}?.owner == authentication?.name")
public @interface PreWriteBankAccount {
   String value(); 
}


// PreAuthorize를 사용하려면  PrePostTemplateDefaults baen 주입 필요
/*

@Bean
PrePostTemplateDefaults prePostTemplateDefaults() {
    return new PrePostTemplateDefaults();
}

 */