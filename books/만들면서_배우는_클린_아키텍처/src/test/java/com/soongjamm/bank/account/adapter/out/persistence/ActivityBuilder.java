package com.soongjamm.bank.account.adapter.out.persistence;

import com.soongjamm.bank.account.domain.AccountId;
import com.soongjamm.bank.account.domain.Activity;
import com.soongjamm.bank.shared.Money;

import java.time.LocalDateTime;

public class ActivityBuilder {

    AccountId id;
    Money money;

    public ActivityBuilder withId(AccountId o) {
        return new ActivityBuilder();
    }

    public ActivityBuilder withMoney(Money of) {
        return  new ActivityBuilder();
    }

    public Activity build() {
        return new Activity(this.id, this.id, this.id, LocalDateTime.now(), money);
    }

    public ActivityBuilder withTargetAccount(AccountId accountId) {
        return null;
    }
}
