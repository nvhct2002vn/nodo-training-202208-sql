package edu.hanoi.jazz.springjazz.controller;

import edu.hanoi.jazz.springjazz.dao.GroupDAO;
import edu.hanoi.jazz.springjazz.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("group")
public class GroupController {

    @Autowired
    private GroupDAO groupDAO;

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(GroupController.class));

    @GetMapping("add")
    public ModelAndView add() {
        return new ModelAndView("form", "command", new Group());
    }

    @PostMapping("add")
    public ModelAndView addNew(@Valid @ModelAttribute("command") Group group, Errors error, @RequestParam("id") Optional<Integer> id) {
        if (error.hasErrors()) {
            ModelAndView mv = new ModelAndView("form");
            mv.addObject("error", error);
            return mv;
        }
        if (id.isPresent()) {
            groupDAO.update(group);
        } else {
            groupDAO.insert(group);
        }
        LOGGER.info("add new group ------------> " + group);
        return new ModelAndView("redirect:/group/list");
    }

    @GetMapping("list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("list", groupDAO.list());
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id) {
        if (null == id) {
            return new ModelAndView("redirect:/group/list");
        }
        groupDAO.delete(id);
        return new ModelAndView("redirect:/group/list");
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();

        Group group = groupDAO.findID(id);
        if (null == group) {
            return new ModelAndView("redirect:/group/list");
        }
        return new ModelAndView("form", "command", group);
    }
}
