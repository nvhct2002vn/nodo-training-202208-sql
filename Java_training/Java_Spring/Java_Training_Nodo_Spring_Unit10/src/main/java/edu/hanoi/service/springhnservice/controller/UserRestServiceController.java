package edu.hanoi.service.springhnservice.controller;


import edu.hanoi.service.springhnservice.dao.GroupDAO;
import edu.hanoi.service.springhnservice.dao.UserDAO;
import edu.hanoi.service.springhnservice.model.Group;
import edu.hanoi.service.springhnservice.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserRestServiceController {

    private static final Logger LOGGER = Logger.getLogger(UserRestServiceController.class);
    @Autowired
    UserDAO userDAO;

    private final GroupDAO groupDAO;

    public UserRestServiceController(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @GetMapping("user/list")
    @PreAuthorize("hasRole('USER')")
//    @PostFilter("filterObject.username == 'user'")
    @PostFilter("hasPermission(filterObject,'read')")
    public List<User> listUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("\n------------------------------> " + auth.getAuthorities());
//        if (!request.isUserInRole("ROLE_ADMIN")) {
//            throw new RuntimeException("Access Denied!");
//        }
        return userDAO.listUser();
    }

    @GetMapping("group/list")
    public Group[] listGroup() {
        return groupDAO.listGroup().toArray(new Group[0]);
    }

    @PostMapping("user/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody User user) {
        return userDAO.insert(user);
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userDAO.getUser(username);
    }

    @GetMapping("user/delete/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        userDAO.delete(username);
    }

    @PostMapping("user/update")
    public void updateUser(@RequestBody User user) {
        userDAO.update(user);
    }
}
