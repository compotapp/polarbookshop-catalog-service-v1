package com.polarbookshop.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "polar")//Помечает класс как источник свойств конфигурации, начинающихся с префикса «полярный».
public class PolarProperties {
    /**
     * A message to welcome users.
     */
    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}

//other examples

//@Autowired
//private Environment environment;
//public String getServerPort() {
//return environment.getProperty("server.port");
//}

//@Value("${server.port}")
//private String serverPort;
//public String getServerPort() {
//return serverPort;
//}

