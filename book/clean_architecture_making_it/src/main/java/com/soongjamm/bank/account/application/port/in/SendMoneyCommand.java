package com.soongjamm.bank.account.application.port.in;

import com.soongjamm.bank.account.domain.AccountId;
import com.soongjamm.bank.shared.Money;
import lombok.Getter;

import javax.validation.constraints.NotNull;

import com.soongjamm.bank.shared.*;

@Getter
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

    @NotNull
    private final AccountId sourceAccountId;
    @NotNull
    private final AccountId targetAccountId;
    @NotNull
    private final Money money;

    public SendMoneyCommand(AccountId sourceAccountId,
                            AccountId targetAccountId,
                            Money money) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
        requireGreaterThan(money, 0);
    }

    private void requireGreaterThan(Money money, int i) {

    }


}
