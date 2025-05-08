package mk.ukim.finki.labs.service.application;

import mk.ukim.finki.labs.dto.auth.LoginDto;
import mk.ukim.finki.labs.dto.auth.RegisterDto;
import mk.ukim.finki.labs.dto.user.DisplayUserDto;
import mk.ukim.finki.labs.model.domain.User;
import mk.ukim.finki.labs.service.domain.UserDomainService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationService {

    private final UserDomainService userDomainService;
    private final PasswordEncoder passwordEncoder;

    public UserApplicationService(UserDomainService userDomainService,
                                  PasswordEncoder passwordEncoder) {
        this.userDomainService = userDomainService;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(RegisterDto dto) {
        if (userDomainService.findByUsername(dto.username()).isPresent()) {
            return false;
        }

        String encodedPassword = passwordEncoder.encode(dto.password());
        User user = new User(dto.username(), encodedPassword, dto.role());
        userDomainService.save(user);
        return true;
    }

    public Optional<User> login(LoginDto dto) {
        return userDomainService.findByUsername(dto.username())
                .filter(user -> passwordEncoder.matches(dto.password(), user.getPassword()));
    }

    public List<DisplayUserDto> findAll() {
        return userDomainService.findAll().stream()
                .map(DisplayUserDto::from)
                .toList();
    }
}
