package com.cb.idp.Auth.SAML.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @RequestMapping(value = {"/"})
    public String selectProvider() {
        //SelectServiceProviderFilter will handle this request
        return "redirect:/saml/idp/select";
    }
}
