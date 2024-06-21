package com.cb.idp.Auth.SAML.controller;



import org.springframework.security.core.Authentication;
import org.springframework.security.saml.SAMLAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {
    @GetMapping("/sso")
    public String samlSso() {
        // Redirect to SAML login page
        return "redirect:/saml/idp";
    }

    @GetMapping("/acs")
    @ResponseBody
    public Authentication samlAcs(SAMLAuthenticationToken token) {
        // Handle assertion consumer service response
        return null;
    }
}
