package bitlab.tech.finish.messenger.controllers.RestControllers;


import bitlab.tech.finish.messenger.dto.UserDTO;
import bitlab.tech.finish.messenger.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/admin")
public class AdminRestController {

    private final UserService userService;

    @PostMapping(value = "/users/toggle/ban")
    public ResponseEntity<?> banUser(@RequestParam(name = "user_id") Long id,
                                     @RequestParam(name = "banned") boolean banned) {
        userService.toggleBan(id, banned);
        return ResponseEntity.ok("User banned");
    }





    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.userListDTO());
    }
}
