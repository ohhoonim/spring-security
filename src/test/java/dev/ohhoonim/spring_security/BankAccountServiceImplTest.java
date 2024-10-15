package dev.ohhoonim.spring_security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;

@SpringBootTest
public class BankAccountServiceImplTest {
   // chapter 1 : spring security는 final에서 동작하지 않는다. 
   // spring bean 은 proxy 생성시 원 클래스를 final 로 만든다. 이문제를 해결하려면 
   // service 를 만들 때 항상 interface를 사용해야 한다.

   // BankAccountService accounts = new BankAccountServiceProxy(new BankAccountServiceImpl());

   // void login(String user) {
   // Authentication auth = new TestingAuthenticationToken(user, "password",
   // "ROLE_USER");
   // SecurityContextHolder.getContext().setAuthentication(auth);
   // }

   // @AfterEach
   // void cleanUp() {
   // SecurityContextHolder.clearContext();
   // }

   // chapter 2 
   // chapter 1을 끝냈으면 proxy로 계속 진행해보자. proxy는 원 클래스를 delegate 한다. 
   // AuthorizationProxyFactory factory = AuthorizationAdvisorProxyFactory.withDefaults();
   // BankAccountService accounts = (BankAccountService)factory.proxy(new BankAccountServiceImpl());

   // chapter 3 이제 스프링부트 빈으로 진행해보자. 
   // BankAccountServiceImpl 에 @Service 를 추가
   @Autowired
   BankAccountService accounts;

   @Test
   // @WithMockUser("rob")
   @WithMockRob
   void findByIdWhenGranted() {
      // login("rob");
      this.accounts.findById(1);
   }

   @Test
   // @WithMockUser("rob")
   @WithMockRob
   void getByIdWhenGranted() {
      // login("rob");
      this.accounts.getById(1);
   }

   @Test
   // @WithMockUser("josh")
   @WithMockJosh
   void findByIdWhenDenied() {
      // login("rot");
      assertThatExceptionOfType(AuthorizationDeniedException.class).isThrownBy(() -> this.accounts.findById(1));
   }

   @Test
   // @WithMockUser("josh")
   @WithMockJosh
   void getByIdWhenDenied() {
      // login("rot");
      assertThatExceptionOfType(AuthorizationDeniedException.class).isThrownBy(() -> this.accounts.getById(1));
   }

   @Test
   @WithMockAccountant
   void findByIdWhenAccountant() {
      BankAccount account = this.accounts.findById(1);
      account.getAccountNumber();
   }


   // @EnableMethodSecurity 까지 작업한 다음 아래 테스트 진행하기.
   // MaskAuthorizationDeniedHandler 참고 
   @Test
   @WithMockAccountant
   void getAccountNumberWhenAccountant() {
      BankAccount account = this.accounts.findById(1);
      assertThat(account.getAccountNumber()).isEqualTo("****");
   }

   @Test
   @WithMockRob
   void getAccountNumberWhenRob() {
      BankAccount account = this.accounts.findById(1);
      assertThat(account.getAccountNumber()).isEqualTo("123");
   }


   // return이 void일때는 어떻게 할까?
   @Test
   @WithMockRob
   void updateIdWhenGranted() {
      BankAccount account = new BankAccount(1, "rob", "123", 543);
      this.accounts.update(account);
   }

   @Test
   @WithMockJosh
   void saveIdWhenGranted() {
      BankAccount account = new BankAccount(1, "rob", "123", 543);
      assertThatExceptionOfType(AuthorizationDeniedException.class)
         .isThrownBy(() -> this.accounts.update(account));
   }
}