package bitlab.tech.finish.messenger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public String index(){
        return "admin/index";
    }


}
