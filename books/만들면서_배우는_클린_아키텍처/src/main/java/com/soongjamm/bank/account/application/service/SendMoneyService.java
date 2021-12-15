package com.soongjamm.bank.account.application.service;

import com.soongjamm.bank.account.application.port.in.SendMoneyCommand;
import com.soongjamm.bank.account.application.port.in.*;
import com.soongjamm.bank.account.application.port.out.*;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class SendMoneyService implements SendMoneyUseCase {

    private final LoadAccountPort loadAccountPort;
    private final AccountLock accountLock;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        // todo 비즈니스 규칙 검증
        // todo 모델 상태 조작
        // todo 출력값 반환
        return false;
    }
}
