package com.ppd.GPSTrackerBackend.controller;

import com.ppd.GPSTrackerBackend.model.User;
import com.ppd.GPSTrackerBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * This means that this class is a Controller
 * The URL will start with /login (after Application path)
 * This endpoint will allow to login into the application.
 * We can also edit an user and delete an user by using specified operations from this endpoint.
 */
@Controller
@RequestMapping(path="/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private UserService userService;

    public LoginController() {
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity login (@RequestBody User login) {
        User user = userService.getUser(login.getEmail(), login.getPassword());
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // return 400 or 500
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    @ResponseBody
    public ResponseEntity getUserById (@PathVariable int id) {
        User u = userService.getUserInfoById(id);
        if(u == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // return 400 or 500
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PutMapping(path="/edit")
    @ResponseBody
    public ResponseEntity updateUser (@RequestBody User user) {
        User res = userService.updateUser(user);
        if(res == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // return 400 or 500
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(path="/del")
    @ResponseBody
    public ResponseEntity deleteUser(Integer id) {
        userService.deleteVehicle(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
