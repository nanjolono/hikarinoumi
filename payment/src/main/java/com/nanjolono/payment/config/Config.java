package com.nanjolono.payment.config;

import org.h2.server.Service;
import org.h2.server.TcpServer;
import org.h2.server.web.WebServer;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Configuration
public class Config {

    @Bean(initMethod="start",destroyMethod = "stop")
    public TcpServer createDatabase() throws SQLException {
        return new TcpServer();
    }
}
