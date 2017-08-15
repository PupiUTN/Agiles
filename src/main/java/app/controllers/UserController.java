/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.exception.EmailExistsException;
import app.exception.PasswordDoesNotMatchException;
import app.models.entities.User;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequestMapping(value = "/api/user/")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * http://www.baeldung.com/get-user-in-spring-security
     * */
    @RequestMapping(method = RequestMethod.GET, value = "/me")
    public ResponseEntity getProfile(HttpServletRequest request) throws Exception {
        Principal principal = request.getUserPrincipal();
        if (principal == null)         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity(principal,HttpStatus.OK);
    }


    /**
     * http://www.baeldung.com/registration-with-spring-mvc-and-spring-security
     * http://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
     * */
    @RequestMapping(method = RequestMethod.POST,value = "/registration")
    public ResponseEntity registerUserAccount(@RequestBody @Valid User user) throws EmailExistsException, PasswordDoesNotMatchException {
        userService.registerNewUserAccount(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}