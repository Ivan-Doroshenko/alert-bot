package com.knubisoft.bot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "crypto")
@Getter
@Setter
public class CryptoConfig {

    private String pricesUrl;
    private String pricesUpdateDelayInMilliseconds;
}
