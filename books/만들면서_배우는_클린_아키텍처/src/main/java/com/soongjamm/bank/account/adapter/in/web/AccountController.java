//package com.soongjamm.bank.account.adapter.in.web;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//class AccountController {
//    // 하나의 클래스에 모두 구현.
//    // -> 테스트가 어렵다.
//    // 병렬작업이 어렵다.(머지 컨플릭트 등)
//    // 클래스의 역할을 알기 어렵다..
//    // 데이터 구조를 재사용할 가능성이 높다. (하나의 객체가 여러 유스케이스에 사용되면, 불필요한 데이터를 가질 가능성이 높다.)
//
//    private final GetAccountBalanceQuery getAccountBalanceQuery;
//    private final ListAccountQuery listAccountQuery;
//    private final LoadAccountQuery loadAccountQuery;
//
//    private final SendMoneyUseCase sendMoneyUseCase;
//    private final CrateAccountUseCase createAccountUseCase;
//
//    @GetMapping("/accounts")
//    List<AccountResource> listAccounts() {
//        ...
//    }
//
//    @GetMapping("/accounts/id")
//    AccountResource getAccount(@PathVariable("accountId") Long accountId) {
//        ...
//    }
//
//    @GetMapping("/accounts/{id}/balance")
//    long getAccountBalance(@PathVariable("accountId") Long accountId) {
//        ...
//    }
//
//    @PostMapping("/accounts")
//    AccountResource createAccount(@RequestBody AccountResource account) {
//        ...
//    }
//
//    @PostMapping("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
//    void sendMoney(
//            @PathVariable("sourceAccountId") Long sourceAccountId,
//            @PathVariable("targetAccountId") Long targetAccountId,
//            @PathVariable("amount") Long amount) {
//        ...
//    }
//
//    )
//}
