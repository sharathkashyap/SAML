package com.cb.idp.Auth.SAML.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "saml2")
@Configuration
public class AppProperties {
}
