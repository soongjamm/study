package com.soongjamm.bank.account.application.service;

import com.soongjamm.bank.account.domain.AccountId;
import com.soongjamm.bank.shared.Money;

public interface GetAccountBalanceQuery {
    Money getAccountBalance(AccountId accountId);
}
