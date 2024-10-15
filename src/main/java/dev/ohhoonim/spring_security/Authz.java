package dev.ohhoonim.spring_security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@Component
public class Authz {
    public boolean check(Authentication authentication, BankAccount account) {
        if (authentication.getName().equals(account.getOwner())) {
            return true;
        }
        return hasRole("ACCOUNTANT").check(() -> authentication, account).isGranted();
    }
}
