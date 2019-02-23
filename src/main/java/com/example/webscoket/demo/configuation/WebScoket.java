package com.example.webscoket.demo.configuation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 注入ServerEndpointExporter之后，
 * 这个bean会自动注册使用@ServerEndpoint注解声明的webScoket
 */
@Configuration
public class WebScoket {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
         return new ServerEndpointExporter ();
    }
}
