package dev.ohhoonim.spring_security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.method.HandleAuthorizationDenied;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

// record 는 final class이므로 @PreAuthorize 사용 불가
// public record BankAccount (
//    long id, 
//    String owner,
//    @PreAuthorize("this.owner == authentication?.name")
//    String accountNumber,
//    double balance
// ) {}

// record BankAccount는 proxy객체가 아니므로(spring이 관리하지 않는 도메인 객체) 
// @PreAuthrize를 사용할 수 없다. class로 변경 후 
// 서비스쪽에서 @AuthorizeReturnObject 를 사용해야 한다.
// PostReadBankAccount 참조

@JsonSerialize(as = BankAccount.class) // Jackson 라리브러리 분제 있으므로 Security 사용시 추가해줘야함 
public class BankAccount {
   private long id;
   private String owner;
   private String accountNumber;
   private double balance;

   public BankAccount(long id, String owner, String accountNumber, double balance) {
      this.id = id;
      this.owner = owner;
      this.accountNumber = accountNumber;
      this.balance = balance;
   }

   public long getId() {
      return id;
   }

   public String getOwner() {
      return owner;
   }

   @PreAuthorize("this.owner == authentication?.name")
   @HandleAuthorizationDenied(handlerClass = MaskAuthorizationDeniedHandler.class)
   public String getAccountNumber() {
      return accountNumber;
   }

   public double getBalance() {
      return balance;
   }

   public void setId(long id) {
      this.id = id;
   }

   public void setOwner(String owner) {
      this.owner = owner;
   }

   public void setAccountNumber(String accountNumber) {
      this.accountNumber = accountNumber;
   }

   public void setBalance(double balance) {
      this.balance = balance;
   }

}