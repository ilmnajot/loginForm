package uz.ilmnajot.loginform.Service;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.loginform.dto.LoginDto;
import uz.ilmnajot.loginform.dto.LoginForm;
import uz.ilmnajot.loginform.dto.UserDto;
import uz.ilmnajot.loginform.dto.UserForm;
import uz.ilmnajot.loginform.enums.Role;
import uz.ilmnajot.loginform.model.User;
import uz.ilmnajot.loginform.repository.UserRepository;
import uz.ilmnajot.loginform.security.jwt.JwtProvider;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto registerUser(UserForm form) {
        Optional<User> optionalUser = userRepository.findUserByEmail(form.getEmail());

        if (optionalUser.isPresent()) {
            return null;
        } else {
            User user = new User();

            user.setName(form.getName());
            user.setUsername(form.getUsername());
            user.setEmail(form.getEmail());
            user.setPassword(passwordEncoder.encode(form.getPassword()));
            user.setAge(form.getAge());
            user.setRole(Role.USER);

            User save = userRepository.save(user);
            return UserDto.toDto(save);
        }
    }

    @Override
    public LoginDto login(@NotNull LoginForm form) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                form.getEmail(),
                form.getPassword()
        ));
        String token = jwtProvider.generateToken(form.getEmail());
        LoginDto loginDto = new LoginDto();
        loginDto.setToken(token);
        return loginDto;
    }
}
