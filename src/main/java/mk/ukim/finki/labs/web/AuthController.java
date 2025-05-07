package mk.ukim.finki.labs.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.labs.dto.auth.LoginDto;
import mk.ukim.finki.labs.dto.auth.RegisterDto;
import mk.ukim.finki.labs.model.domain.User;
import mk.ukim.finki.labs.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserApplicationService userApplicationService;

    public AuthController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register new user", description = "Register with username, password, and role.")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto dto) {
        boolean success = userApplicationService.register(dto);
        if (success) {
            return ResponseEntity.ok("User registered successfully.");
        } else {
            return ResponseEntity.badRequest().body("Username already exists.");
        }
    }

    @Operation(summary = "Login user", description = "Login with username and password.")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto dto) {
        Optional<User> user = userApplicationService.login(dto);
        if (user.isPresent()) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials.");
        }
    }

    @Operation(summary = "Logout user", description = "Invalidate session.")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(Principal principal) {
        return ResponseEntity.ok("Logged out: " + (principal != null ? principal.getName() : "unknown"));
    }
}
