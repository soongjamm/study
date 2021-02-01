package config;

import board.BoardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ControllerConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public BoardController boardController() {
        BoardController boardController = new BoardController();
        boardController.setDataSource(dataSource);
        return boardController;
    }
}
