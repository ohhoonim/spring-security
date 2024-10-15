package dev.ohhoonim.spring_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController {
   final BankAccountService accounts;
   
   public BankAccountController(BankAccountService accounts) {
      this.accounts = accounts;
   }

    @GetMapping("/accounts/{id}")   
    BankAccount findById(@PathVariable("id") Long id) {
        return accounts.findById(id);
    }
}
