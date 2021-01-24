package com.soongjamm.football_manager.DB;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("DB 연결합니다.");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DB 연결을 해지합니다.");
    }
}
