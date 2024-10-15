package dev.ohhoonim.spring_security;

import java.security.Principal;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

public class BankAccountServiceProxy implements BankAccountService {

    private final BankAccountService delegate; 

    public BankAccountServiceProxy(BankAccountService delegate) {
        this.delegate = delegate;
    }

    @Override
    public BankAccount findById(long id) {
        BankAccount account = delegate.findById(id);

        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        if (!principal.getName().equals(account.getOwner())) {
            throw new AuthorizationDeniedException("Denied", new AuthorizationDecision(false));
        }
        return account;
    }

    @Override
    public BankAccount getById(long id) {
        BankAccount account = delegate.findById(id);

        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        if (!principal.getName().equals(account.getOwner())) {
            throw new AuthorizationDeniedException("Denied", new AuthorizationDecision(false));
        }
        return  account;
    }

}
