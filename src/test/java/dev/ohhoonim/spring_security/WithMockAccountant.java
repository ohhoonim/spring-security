package dev.ohhoonim.spring_security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithMockUser;

@Retention(RetentionPolicy.RUNTIME) 
@WithMockUser(roles = "ACCOUNTANT")
public @interface WithMockAccountant {
    
}
