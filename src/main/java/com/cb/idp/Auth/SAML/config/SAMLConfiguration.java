package com.cb.idp.Auth.SAML.config;

import com.cb.idp.Auth.SAML.filters.CustomIDPInitiatedLoginFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.saml.provider.SamlServerConfiguration;
import org.springframework.security.saml.provider.identity.config.SamlIdentityProviderServerBeanConfiguration;


public class SAMLConfiguration extends SamlIdentityProviderServerBeanConfiguration {

    private final AppProperties config;

    public SAMLConfiguration(@Qualifier("appProperties") AppProperties config) {
        this.config = config;
    }

    @Override
    protected SamlServerConfiguration getDefaultHostSamlServerConfiguration() {
        return null;
    }

   /* @Bean
    public UserDetailsService userDetailsService() {
        Collection<IDPUserDetails> allUsers = UserUtils.getAllUserLoginDetails();
        return new IDPInMemoryDetailsManager(allUsers);
    }*/

   /* @Bean
    public Filter idpInitatedLoginFilter() {
        return new CustomIDPInitiatedLoginFilter(getSamlProvisioning(), samlAssertionStore());
    }


    @Bean
    public Filter idpAuthnRequestFilter() {
        return new IdpAuthenticationRequestFilter(getSamlProvisioning(), samlAssertionStore());
    }*/
}
