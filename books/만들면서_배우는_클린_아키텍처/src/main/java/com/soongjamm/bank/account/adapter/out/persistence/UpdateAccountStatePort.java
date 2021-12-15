package com.soongjamm.bank.account.adapter.out.persistence;

import com.soongjamm.bank.account.domain.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);

}
