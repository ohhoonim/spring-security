package dev.ohhoonim.spring_security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.lang.Nullable;
import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.security.authorization.method.MethodAuthorizationDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class MaskAuthorizationDeniedHandler implements MethodAuthorizationDeniedHandler{

    @Override
    @Nullable
    public Object handleDeniedInvocation(MethodInvocation methodInvocation, AuthorizationResult authorizationResult) {
        return  "****";
    }
    
}

// BankAccount.java 참조 
