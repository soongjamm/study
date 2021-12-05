package com.soongjamm.bank.account.adapter.out.persistence;

import com.soongjamm.bank.account.domain.*;

import java.time.LocalDateTime;

public interface LoadAccountPort {

    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
