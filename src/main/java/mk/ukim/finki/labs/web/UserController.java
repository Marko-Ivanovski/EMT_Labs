package mk.ukim.finki.labs.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import mk.ukim.finki.labs.dto.user.DisplayUserDto;
import mk.ukim.finki.labs.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    // GET /api/users
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @Operation(summary = "Get all users (without reservations)")
    public ResponseEntity<List<DisplayUserDto>> findAll() {
        return ResponseEntity.ok(userApplicationService.findAll());
    }
}

