package edu.java.spring.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.print.attribute.standard.Media;

@Controller
@RequestMapping("/hello")
public class HelloClazzController {
    //11 12
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printMessage() {
        ModelAndView mv = new ModelAndView();
//        mv.setViewName("index");
//        mv.addObject("message", "Hello Java Clazz!");
        mv.setViewName("clazz");
        mv.addObject("name", "Nguyen Viet Hien!");
        return mv;
    }

    //13
    @RequestMapping(value = "fb", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:https://www.facebook.com";
    }

    @RequestMapping(value = "data", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String raw() {
        return "Xin chao moi nguoi";
    }

    
}
