package mk.ukim.finki.labs.service.application;

import mk.ukim.finki.labs.dto.auth.LoginDto;
import mk.ukim.finki.labs.dto.auth.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationApplicationService {

    public boolean login(LoginDto loginDto) {
        return "user".equals(loginDto.getUsername()) && "user".equals(loginDto.getPassword());
    }

    public boolean register(RegisterDto registerDto) {
        return true;
    }
}
