package mk.ukim.finki.labs.dto.user;

import mk.ukim.finki.labs.model.domain.User;
import mk.ukim.finki.labs.model.enumerations.Role;

public record DisplayUserDto(Long id, String username, Role role) {
    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(user.getId(), user.getUsername(), user.getRole());
    }
}
