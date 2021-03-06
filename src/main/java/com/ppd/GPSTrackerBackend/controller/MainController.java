package com.ppd.GPSTrackerBackend.controller;

import com.ppd.GPSTrackerBackend.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This means that this class is a Controller
 * The URL will start with / (after Application path)
 * This endpoint will allow us to check if the server is working
 */
@Controller
@RequestMapping(path="/")
public class MainController {
    // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    @Autowired
    private UserRepository userRepository;

    public MainController() {
    }

    @GetMapping(path="/hello") // Map ONLY POST Requests
    @ResponseBody
    public String sayHello () {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        return "Hello world !";
    }
}
