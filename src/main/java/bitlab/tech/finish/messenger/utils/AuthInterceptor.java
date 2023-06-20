package bitlab.tech.finish.messenger.utils;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            User authUser = userService.getCurrentSessionUser();
            request.setAttribute("globalAuthUser", authUser);
            modelAndView.addObject("globalAuthUser", authUser);
        }
    }
}