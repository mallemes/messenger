package bitlab.tech.finish.messenger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @RequestMapping(value = "/")
    public String index(){
        return "admin/index";
    }

    @RequestMapping(value = "/users")
    public String users(){
        return "admin/users";
    }


}
