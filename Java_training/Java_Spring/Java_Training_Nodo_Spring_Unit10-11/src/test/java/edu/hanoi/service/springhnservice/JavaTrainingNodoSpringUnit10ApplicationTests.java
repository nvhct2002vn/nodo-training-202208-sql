package edu.hanoi.service.springhnservice;

import edu.hanoi.service.springhnservice.controller.UserRestServiceController;
import edu.hanoi.service.springhnservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class JavaTrainingNodoSpringUnit10ApplicationTests {

    @Autowired
    UserRestServiceController restServiceController;

    @Test
    void contextLoads() {
        List<User> users = restServiceController.listUser();
        Assert.isTrue(users.size() > 0);
    }

}
