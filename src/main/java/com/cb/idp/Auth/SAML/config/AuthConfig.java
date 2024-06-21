package com.cb.idp.Auth.SAML.config;

import org.apache.velocity.app.VelocityEngine;
import org.opensaml.saml2.metadata.provider.FilesystemMetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.saml2.metadata.provider.ResourceBackedMetadataProvider;
import org.opensaml.util.resource.ClasspathResource;
import org.opensaml.util.resource.ResourceException;
import org.opensaml.xml.parse.BasicParserPool;
import org.opensaml.xml.parse.ParserPool;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.saml.SAMLAuthenticationProvider;
import org.springframework.security.saml.key.JKSKeyManager;
import org.springframework.security.saml.log.SAMLDefaultLogger;
import org.springframework.security.saml.log.SAMLLogger;

import org.springframework.security.saml.metadata.CachingMetadataManager;
import org.springframework.security.saml.metadata.ExtendedMetadata;
import org.springframework.security.saml.metadata.ExtendedMetadataDelegate;


import org.springframework.security.saml.metadata.MetadataManager;
import org.springframework.security.saml.util.VelocityFactory;
import org.springframework.security.saml.websso.WebSSOProfileConsumer;
import org.springframework.security.saml.websso.WebSSOProfileConsumerImpl;
import java.io.File;
import java.util.*;

@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/saml/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .httpBasic().disable();
    }


    @Bean
    public SAMLAuthenticationProvider samlAuthenticationProvider() {
        return new SAMLAuthenticationProvider();
    }


    @Bean
    public ExtendedMetadataDelegate idpMetadataProvider() throws Exception {
        ResourceBackedMetadataProvider provider = new ResourceBackedMetadataProvider(
                new Timer(), new ClasspathResource("/idp-metadata.xml"));
        provider.setParserPool(parserPool());
        ExtendedMetadataDelegate extendedMetadataDelegate= new ExtendedMetadataDelegate(provider, extendedMetadata());
        extendedMetadataDelegate.setMetadataTrustCheck(true);
        extendedMetadataDelegate.setMetadataRequireSignature(false);
        return extendedMetadataDelegate;
    }

    @Bean
    public MetadataProvider metadataProvider() throws Exception {
        File metadataFile = new File("/idp-metadata.xml");
        FilesystemMetadataProvider provider = new FilesystemMetadataProvider(metadataFile);
        provider.setParserPool(parserPool());
        return provider;
    }



    @Bean
    public ExtendedMetadataDelegate spMetadataProvider() throws Exception {
        ResourceBackedMetadataProvider provider = new ResourceBackedMetadataProvider(
                new Timer(), new ClasspathResource("sp-metadata.xml"));
        provider.setParserPool(parserPool());
        return new ExtendedMetadataDelegate(provider, extendedMetadata());
    }

    @Bean
    public WebSSOProfileConsumer webSSOprofileConsumer() {
        return new WebSSOProfileConsumerImpl();
    }

    @Bean
    public MetadataManager metadataManager() throws Exception {
        List<MetadataProvider> providers = new ArrayList<>();
        providers.add(idpMetadataProvider());
        return new CachingMetadataManager(providers);
    }

    @Bean
    public ParserPool parserPool() {
        return new BasicParserPool();
    }

    @Bean
    public ExtendedMetadata extendedMetadata() {
        ExtendedMetadata extendedMetadata = new ExtendedMetadata();
        extendedMetadata.setIdpDiscoveryEnabled(true);
        return extendedMetadata;
    }


    @Bean
    public VelocityEngine velocityEngine() {
        return VelocityFactory.getEngine();
    }

    @Bean
    public SAMLLogger samlLogger() {
        return new SAMLDefaultLogger();
    }

    @Bean
    public JKSKeyManager keyManager() {

        Resource storeFile;
        storeFile = new ClassPathResource("/idp-saml.jks");

        String storePass = "welcome";
        Map<String, String> passwords = new HashMap<>();
        passwords.put("SAMLKey", "welcome");
        String defaultKey = "SAMLKey";
        return new JKSKeyManager(storeFile, storePass, passwords, defaultKey);
    }

}


