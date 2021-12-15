package com.soongjamm.bank.account.adapter.out.persistence;

import com.soongjamm.bank.account.domain.Account;
import com.soongjamm.bank.account.domain.AccountId;
import com.soongjamm.bank.account.domain.ActivityWindow;
import com.soongjamm.bank.shared.Money;

public class AccountBuilder {
    public AccountBuilder withBaselineBalance(Money of) {
        return new AccountBuilder();
    }

    public AccountBuilder withActivityWindow(ActivityWindow of) {
        return new AccountBuilder();
    }


    public Account build() {
        return null;
    }

    public AccountBuilder withAccountId(AccountId accountId) {
        return null;
    }
}
