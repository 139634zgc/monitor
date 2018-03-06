package com.bitauto.bdc.modules.oozieDashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by michealzhang on 2017/8/9.
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


}
