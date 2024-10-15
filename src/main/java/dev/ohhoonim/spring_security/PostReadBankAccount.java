package dev.ohhoonim.spring_security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authorization.method.AuthorizeReturnObject;

@Retention(RetentionPolicy.RUNTIME)
// @PostAuthorize("returnObject?.owner == authentication?.name or hasRole('ACCOUNTANT')")
// 위에거를 아래거로 대체
@PostAuthorize("@authz.check(authentication, returnObject)")
@AuthorizeReturnObject
public @interface PostReadBankAccount {
    
}

// org.springframework.security.access.expression.SecurityExpressionRoot

// 도메인 객체를 security 검사하려면 @AuthorizeReturnObject 추가
// *Application.java (main메소드 있는 클래스) 에 @EnableMethodSecurity 추가 까먹지 말기