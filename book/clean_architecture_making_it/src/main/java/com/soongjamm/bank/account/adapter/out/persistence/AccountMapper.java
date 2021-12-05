package com.soongjamm.bank.account.adapter.out.persistence;

import com.soongjamm.bank.account.domain.*;

import java.util.List;

public interface AccountMapper {
    Account mapToDomainEntity(AccountJpaEntity account, List<ActivityJpaEntity> activities, Long withdrawalBalance, Long depositBalance);

    ActivityJpaEntity mapToJpaEntity(Activity activity);
}
