package com.cb.idp.Auth.SAML.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.saml.provider.identity.config.SamlIdentityProviderSecurityConfiguration;

public class SecurityConfig {
    @Configuration
    @Order(1)
    public static class SamlSecurity extends SamlIdentityProviderSecurityConfiguration {

        private final Object appProperties;
        private final SAMLConfiguration samlConfig;

        public SamlSecurity(SAMLConfiguration samlConfig, @Qualifier("appProperties") Object appProperties) {
            super("/saml/idp/", samlConfig);
            this.appProperties = appProperties;
            this.samlConfig = samlConfig;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            super.configure(http);
            /*http.
                    userDetailsService(samlConfig.userDetailsService())
                    .formLogin();

            http.
                    apply(identityProvider())
                    .configure(appProperties);*/
        }
    }

    @Configuration
    public static class AppSecurity {

        private final SAMLConfiguration samlConfig;

        public AppSecurity(SAMLConfiguration samlConfig) {
            this.samlConfig = samlConfig;
        }

        /*@Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/**")
                    .authorizeRequests()
                    .antMatchers("/**").authenticated()
                    .and()
                    .userDetailsService(samlConfig.userDetailsService()).formLogin()
            ;
        }*/
    }
}
