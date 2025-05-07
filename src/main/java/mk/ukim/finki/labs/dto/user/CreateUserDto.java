package mk.ukim.finki.labs.dto.user;

import mk.ukim.finki.labs.model.domain.User;
import mk.ukim.finki.labs.model.enumerations.Role;

public record CreateUserDto(String username, String password, Role role) {
    public User toUser(String encodedPassword) {
        return new User(username, encodedPassword, role);
    }
}
