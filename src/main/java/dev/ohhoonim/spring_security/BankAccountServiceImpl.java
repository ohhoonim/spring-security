package dev.ohhoonim.spring_security;

import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    public BankAccount findById(long id) {
        var account = new BankAccount(id, "rob", "123", 543);
        return account;
    }

    public BankAccount getById(long id) {
        return findById(id);
    }
}
