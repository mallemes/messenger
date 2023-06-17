package bitlab.tech.finish.messenger.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFoundException() {
        ModelAndView modelAndView = new ModelAndView("handlers/404-page");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleException() {
//        ModelAndView modelAndView = new ModelAndView("handlers/500-page");
//        modelAndView.setStatus(HttpStatus.E);
//        return modelAndView;
//    }
}
