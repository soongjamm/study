package com.soongjamm.bank.account.application.service;

import com.soongjamm.bank.account.application.port.in.SendMoneyCommand;
import com.soongjamm.bank.account.domain.Account;
import com.soongjamm.bank.account.domain.AccountId;
import com.soongjamm.bank.shared.Money;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;

class SendMoneyServiceTest {

    private SendMoneyService sendMoneyService;
    private AccountLock accountLock;

    @Test
    void transactionSucceeds() {

        Account sourceAccount = givenSourceAccount();
        Account targetAccount = givenTargetAccount();

        givenWithdrawalWillSucceed(sourceAccount);
        givenDepositWillSucceed(targetAccount);

        Money money = Money.of(500L);

        SendMoneyCommand command = new SendMoneyCommand(
                sourceAccount.getId(),
                targetAccount.getId(),
                money);

        boolean success = sendMoneyService.sendMoney(command);

        assertThat(success).isTrue();

        AccountId sourceAccountId = sourceAccount.getId();
        AccountId targetAccountId = targetAccount.getId();

        then(accountLock).should().lockAccount(eq(sourceAccountId));
        then(targetAccount).should().deposit(eq(money), eq(sourceAccountId));
        then(accountLock).should().releaseAccount(eq(targetAccountId));

        thenAccountsHaveBeenUpdated(sourceAccountId, targetAccountId);
    }

    private void thenAccountsHaveBeenUpdated(AccountId sourceAccountId, AccountId targetAccountId) {

    }

    private void givenDepositWillSucceed(Account targetAccount) {

    }

    private void givenWithdrawalWillSucceed(Account sourceAccount) {
    }

    private Account givenTargetAccount() {
        return null;
    }

    private Account givenSourceAccount() {
        return null;
    }
}