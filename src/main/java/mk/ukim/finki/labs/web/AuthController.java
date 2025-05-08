package mk.ukim.finki.labs.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.labs.config.security.JwtTokenProvider;
import mk.ukim.finki.labs.dto.auth.LoginDto;
import mk.ukim.finki.labs.dto.auth.RegisterDto;
import mk.ukim.finki.labs.model.domain.User;
import mk.ukim.finki.labs.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserApplicationService userApplicationService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserApplicationService userApplicationService,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider) {
        this.userApplicationService = userApplicationService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
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

    @Operation(summary = "Login user", description = "Login and return JWT token.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
            );

            User user = (User) authentication.getPrincipal();
            String token = jwtTokenProvider.generateToken(user.getUsername());

            return ResponseEntity.ok(token);

        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Invalid credentials.");
        }
    }

    @Operation(summary = "Logout user", description = "No-op for JWT.")
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logged out.");
    }
}
