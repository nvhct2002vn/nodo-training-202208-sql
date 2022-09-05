package edu.hanoi.service.springhnservice;

import edu.hanoi.service.springhnservice.dao.UserDAO;
import edu.hanoi.service.springhnservice.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class HNServicePermissionEvaluator implements PermissionEvaluator {

    private static final Logger LOGGER = Logger.getLogger(HNServicePermissionEvaluator.class);



    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        LOGGER.info("-------->" + targetDomainObject + " : " + permission);
        if (targetDomainObject instanceof User) {
            User user = (User) targetDomainObject;
            return user.getAge() > 50;
        }
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        LOGGER.info("-------->" + targetType + " : " + permission);
        return true;
    }



}
