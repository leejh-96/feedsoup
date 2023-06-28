package com.feed.feedsoup.dbtest;

import com.feed.feedsoup.message.ValidMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
@Slf4j
public class DbConnectionTest {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String name;

    @Value("${spring.datasource.password}")
    private String password;

    @Test
    @DisplayName("dbConnection 테스트")
    void dbConnectionTest() throws SQLException {
        Connection connection = DriverManager.getConnection(url,name,password);
        log.info("connection: {}", connection);
    }

}
