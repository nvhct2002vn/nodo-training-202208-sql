package edu.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class StudentController {

    @GetMapping("/student/add")
    public ModelAndView add() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("studentForm");
//        return mv;
        return new ModelAndView("studentForm", "command", new Student());
    }

//    @PostMapping("/student/save")
//    public ModelAndView save(@RequestParam(value = "name") String name, @RequestParam(value = "age") String age) {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("name", name);
//        mv.addObject("age", age);
//        mv.setViewName("studentView");
//        return mv;
//    }

//    @PostMapping("/student/save")
//    public ModelAndView save(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age) {
//        ModelAndView mv = new ModelAndView();
//        Student student = new Student(name, age);
//        mv.addObject("student", student);
//        mv.setViewName("studentView");
//        return mv;
//    }

//    @PostMapping("/student/save")
//    public ModelAndView save(Student student) {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("student", student);
//        mv.setViewName("studentView");
//        return mv;
//    }

    @PostMapping("/student/save")
    public ModelAndView save(@Valid @ModelAttribute("command") Student student, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()) {
            mv = new ModelAndView("studentForm", "command", student);
            mv.addObject("errors", result);
            return mv;
        }
        mv.addObject("student", student);
        mv.setViewName("studentView");
        return mv;
    }

}
