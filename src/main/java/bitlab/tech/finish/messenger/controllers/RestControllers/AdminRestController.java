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

    @PostMapping(value = "/ban")
    public ResponseEntity<?> banUser(RequestParam id) {
        return ResponseEntity.ok("User banned");
    }

    @PostMapping(value = "/unban")
    public ResponseEntity<?> unbanUser(RequestParam id) {
        return ResponseEntity.ok("User unbanned");
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.userListDTO());
    }
}
