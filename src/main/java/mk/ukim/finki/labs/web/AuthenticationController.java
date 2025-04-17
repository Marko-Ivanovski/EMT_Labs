package mk.ukim.finki.labs.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.labs.dto.auth.LoginDto;
import mk.ukim.finki.labs.dto.auth.RegisterDto;
import mk.ukim.finki.labs.service.application.AuthenticationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationApplicationService authenticationService;

    public AuthenticationController(AuthenticationApplicationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // POST /api/auth/login
    @Operation(summary = "Login user", description = "Authenticate the user by username and password.")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        boolean success = authenticationService.login(loginDto);
        return success ? ResponseEntity.ok("Login successful") : ResponseEntity.status(401).body("Invalid credentials");
    }

    // POST /api/auth/register
    @Operation(summary = "Register new user", description = "Register a new user with username, email, password, and role.")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        boolean success = authenticationService.register(registerDto);
        return success ? ResponseEntity.ok("Registration successful") : ResponseEntity.status(400).body("Registration failed");
    }
}
