package com.soongjamm.bank.account;

import com.soongjamm.bank.account.domain.Account;
import com.soongjamm.bank.account.domain.AccountId;
import com.soongjamm.bank.shared.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SendMoneySystemTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private ResponseEntity<Object> response;

    @Test
    @Sql("SendMoneySystemTest.sql")
    void sendMoney() {

        Money initialSourceBalance  = sourceAccount().calculateBalance();
        Money initialTargetBalance  = targetAccount().calculateBalance();

        whenSendMoney(sourceAccountId(), targetAccountId(), transferredAmount());

        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        then(sourceAccount().calculateBalance()).isEqualTo(initialSourceBalance.minus(transferredAmount()));

        then(targetAccount().calculateBalance()).isEqualTo(initialTargetBalance.plus(transferredAmount()));

    }

    private Money transferredAmount() {
        return null;
    }

    private AccountId targetAccountId() {
        return null;
    }

    private AccountId sourceAccountId() {
        return null;
    }

    private Account targetAccount() {
        return null;
    }

    private Account sourceAccount() {
        return null;
    }

    private ResponseEntity whenSendMoney(AccountId sourceAccountId, AccountId targetAccountId, Money amount) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Void> request = new HttpEntity<>(null, headers);

        return restTemplate.exchange(
                "accounts/sendMoney/{sourceAccountId}/{targetAccountId}/{amount}",
                HttpMethod.POST,
                request,
                Object.class,
                sourceAccountId.getValue(),
                targetAccountId.getValue(),
                amount.getAmount());

    }
}
