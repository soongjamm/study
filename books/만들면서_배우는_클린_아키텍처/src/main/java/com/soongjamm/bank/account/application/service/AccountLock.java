package com.soongjamm.bank.account.application.service;

import com.soongjamm.bank.account.domain.AccountId;

public interface AccountLock {
    void lockAccount(AccountId eq);

    void releaseAccount(AccountId eq);
}
