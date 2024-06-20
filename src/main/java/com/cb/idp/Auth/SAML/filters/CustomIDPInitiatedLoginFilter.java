package com.cb.idp.Auth.SAML.filters;


import org.springframework.security.core.Authentication;
import org.springframework.security.saml.SamlRequestMatcher;

import org.springframework.security.saml.provider.identity.IdentityProviderService;
import org.springframework.security.saml.provider.identity.IdpInitiatedLoginFilter;
import org.springframework.security.saml.provider.provisioning.SamlProviderProvisioning;
import org.springframework.security.saml.saml2.authentication.Assertion;
import org.springframework.security.saml.saml2.authentication.AuthenticationRequest;
import org.springframework.security.saml.saml2.metadata.NameId;
import org.springframework.security.saml.saml2.metadata.ServiceProviderMetadata;



public class CustomIDPInitiatedLoginFilter {
   /* public CustomIDPInitiatedLoginFilter(SamlProviderProvisioning<IdentityProviderService> provisioning,
                                         SamlMessageStore<Assertion, HttpServletRequest> assertionStore) {
        super(provisioning, assertionStore);
    }

    public CustomIDPInitiatedLoginFilter(SamlProviderProvisioning<IdentityProviderService> provisioning,
                                         SamlMessageStore<Assertion, HttpServletRequest> assertionStore, SamlRequestMatcher requestMatcher) {
        super(provisioning, assertionStore, requestMatcher);
    }

    @Override
    protected Assertion getAssertion(Authentication authentication,
                                     AuthenticationRequest authenticationRequest,
                                     IdentityProviderService provider,
                                     ServiceProviderMetadata recipient) {
        Assertion assertion = (Assertion) provider.assertion(recipient, authentication.getName(), NameId.PERSISTENT);
        *//*UserUtils.getCurrentUserDetails(SecurityContextHolder.getContext().getAuthentication())
                .getSamlAttributesToSendToSP()
                .forEach(assertion::addAttribute);*//*
        return assertion;
    }*/
}
