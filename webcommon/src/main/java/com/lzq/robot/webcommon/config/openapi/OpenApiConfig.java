package com.lzq.robot.webcommon.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {
    @Value("${openapi3.server.url: http://localhost:8080}")
    String serverUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl(serverUrl);
        List<Server> serverList = new ArrayList<>();
        serverList.add(server);
        return new OpenAPI()
                .components(new Components())
                .servers(serverList)
                .info(new Info().title("Robot API").description(
                        " Robot OpenAPI 3."));
    }
}