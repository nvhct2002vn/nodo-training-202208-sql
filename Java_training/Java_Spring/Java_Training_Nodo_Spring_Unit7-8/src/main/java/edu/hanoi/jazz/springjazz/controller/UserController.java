package edu.hanoi.jazz.springjazz.controller;

import edu.hanoi.jazz.springjazz.dao.GroupDAO;
import edu.hanoi.jazz.springjazz.dao.UserDAO;
import edu.hanoi.jazz.springjazz.model.Group;
import edu.hanoi.jazz.springjazz.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private UserDAO userDAO;

    @GetMapping("form")
    public ModelAndView addForm() {
        ModelAndView mv = new ModelAndView("userForm", "command", new User());
        mv.addObject("groups", toGroupMap(groupDAO.list()));
        return mv;
    }

    private Object toGroupMap(List<Group> groups) {
        Map<Integer, String> map = new HashMap<>();
        groups.forEach(group -> map.put(group.getId(), group.getName()));
        return map;
    }

    @PostMapping("add")
    public ModelAndView addUser(@ModelAttribute("command") User user, @RequestParam("username") Optional<String> username) {
        ModelAndView mv = new ModelAndView();
        if (username.isEmpty()) {
            userDAO.insert(user);
            LOGGER.info("Add new user ----------------> " + user);
        } else {
            userDAO.update(user);
        }
        return new ModelAndView("redirect:/user/list");
    }

    @GetMapping("edit/{username}")
    public ModelAndView edit(@PathVariable("username") String username) {
        ModelAndView mv = new ModelAndView("userForm");
        mv.addObject("groups", toGroupMap(groupDAO.list()));
        mv.addObject("command", userDAO.get(username));
        return mv;
    }

    @GetMapping("list")
    public ModelAndView listUser(@RequestParam("q") Optional<String> q) {
        ModelAndView mv = new ModelAndView();
        if (q.isPresent()) {
            mv.addObject("listUser", userDAO.listByUsername(q.get()));
        } else {
            mv.addObject("listUser", userDAO.list());
        }
        mv.setViewName("listUser");
        return mv;
    }
}
