package com.soongjamm.bank.account.adapter.out.persistence;

import com.soongjamm.bank.account.domain.Account;
import com.soongjamm.bank.account.domain.AccountId;
import com.soongjamm.bank.account.domain.ActivityWindow;
import com.soongjamm.bank.shared.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({AccountPersistenceAdapter.class, AccountMapper.class}) // 이 객체 네트워크를 인스턴스화 한다고 알려준다.
class AccountPersistenceAdapterTest {

    @Autowired
    private AccountPersistenceAdapter adapter;

    @Autowired
    private ActivityRepository activityRepository;
    private AccountId accountId;

    @Test
    @Sql("AccountPersistenceAdapterTest.sql")
    void loadsAccount() {
        Account account = adapter.loadAccount(new AccountId(1L), LocalDateTime.of(2018, 8, 10, 0, 0));

        assertThat(account.getActivityWindow().getActivities()).hasSize(2);
        assertThat(account.calculateBalance()).isEqualTo(Money.of(500L));
    }

    @Test
    void updateActivities() {
        Account account = defaultAccount()
                .withBaselineBalance(Money.of(555L))
                .withActivityWindow(new ActivityWindow(
                        defaultActivity()
                                .withId(null)
                                .withMoney(Money.of(1L)).build(), defaultActivity().withTargetAccount(accountId).withMoney(Money.of(1L)).build()))
                .build();

        adapter.updateActivities(account);

        assertThat(activityRepository.count()).isEqualTo(1);

        ActivityJpaEntity savedActivity = activityRepository.findAll().get(0);
        assertThat(savedActivity.getAmount()).isEqualTo(1L);
    }

    private ActivityBuilder defaultActivity() {
        return null;
    }

    private AccountBuilder defaultAccount() {
        return null;
    }

}