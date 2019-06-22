package org.msgtu.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Wonshine on 2017-07-05.
 */
@RestController
public class SystemController {
    @RequestMapping("/")
    public String  index() {
        return "index.html";
    }


}
