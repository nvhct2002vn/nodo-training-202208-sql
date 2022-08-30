package edu.hanoi.jazz.springjazz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("message", "Hello class java");
        return mv;
    }

    @GetMapping("nguoi-dung")
    public ModelAndView forUser() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "This is protected page!");
        model.setViewName("index");
        return model;
    }

    @GetMapping("dang-nhap")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("login");
        if (error != null) {
            mv.addObject("error", error);
        }
        return mv;
    }
}
