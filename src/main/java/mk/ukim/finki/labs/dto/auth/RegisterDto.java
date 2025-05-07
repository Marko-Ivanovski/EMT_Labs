package mk.ukim.finki.labs.dto.auth;

import mk.ukim.finki.labs.model.enumerations.Role;

public record RegisterDto(
        String username,
        String password,
        Role role
) {}
