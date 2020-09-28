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
 * The URL will start with /subs (after Application path)
 * This endpoint will allow to subscribe into the application.
 * We can also get all existing user in database by using specified operation from this endpoint.
 */
@Controller
@RequestMapping(path="/subs")
@CrossOrigin
public class SubscribeController {

    @Autowired
    private UserService userService;

    public SubscribeController() {
    }

    @PostMapping(path="/add")
    @ResponseBody
    public ResponseEntity addNewUser (@RequestBody User user) {
        User created = userService.addNewUser(user);
        if(created == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // return 400 or 500
        }
        return new ResponseEntity<>(created, HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/all")
    @ResponseBody
    public ResponseEntity getAllUsers() {
        Iterable<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
