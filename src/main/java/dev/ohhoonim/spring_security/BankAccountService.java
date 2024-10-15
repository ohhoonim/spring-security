package dev.ohhoonim.spring_security;

public interface BankAccountService {
    // @PostAuthorize("returnObject?.owner == authentication?.name ")
    @PostReadBankAccount
    BankAccount findById(long id);

    // @PostAuthorize("returnObject?.owner == authentication?.name")
    @PostReadBankAccount
    BankAccount getById(long id);

    // @PreAuthorize("#toSave?.owner == authentication?.name")
    @PreWriteBankAccount("#toSave")
    default void save(BankAccount toSave) {
    }

    // @PreAuthorize("#toSave?.owner == authentication?.name")
    @PreWriteBankAccount("#toUpdate")
    default void update(BankAccount toUpdate) {
    }

}
