package uz.ilmnajot.loginform.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.loginform.Service.UserService;
import uz.ilmnajot.loginform.dto.LoginDto;
import uz.ilmnajot.loginform.dto.LoginForm;
import uz.ilmnajot.loginform.dto.UserDto;
import uz.ilmnajot.loginform.dto.UserForm;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public HttpEntity<UserDto>  registerUser(@RequestBody UserForm form){
        UserDto userDto = userService.registerUser(form);
        return ResponseEntity.ok(userDto);
    }
    @PostMapping("/login")
    public HttpEntity<LoginDto> login(@RequestBody LoginForm form){
        return ResponseEntity.ok(userService.login(form));
    }
}
