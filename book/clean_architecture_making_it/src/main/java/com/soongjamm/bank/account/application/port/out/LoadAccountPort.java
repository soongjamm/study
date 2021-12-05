package com.soongjamm.bank.account.application.port.out;

import com.soongjamm.bank.account.domain.Account;
import com.soongjamm.bank.account.domain.AccountId;

import java.time.LocalDateTime;

public interface LoadAccountPort {
    Account loadAccount(AccountId accountId, LocalDateTime now);
}
