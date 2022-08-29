package edu.hanoi.springclazz.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Controller
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(HomeController.class));

//    @GetMapping("/")
//    @ResponseBody
//    String home() {
//        return "Hello EM";
//    }

    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("message", "Hello Class Java");
        return "/index";
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();

        LOGGER.info("=====================> Đã truy cập vào đây");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) Error error) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login");
        if (error != null) {
            mv.addObject("error", "Sai tai khoan hoac mat khau");
        }
        return mv;
    }

    @GetMapping("nguoi-dung")
    public ModelAndView forUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "Hello User" + authentication.getName());
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
}
