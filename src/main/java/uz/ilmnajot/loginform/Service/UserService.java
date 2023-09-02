package uz.ilmnajot.loginform.Service;

import org.springframework.stereotype.Service;
import uz.ilmnajot.loginform.dto.LoginDto;
import uz.ilmnajot.loginform.dto.LoginForm;
import uz.ilmnajot.loginform.dto.UserDto;
import uz.ilmnajot.loginform.dto.UserForm;


public interface UserService  {
    UserDto registerUser(UserForm form);

    LoginDto login(LoginForm form);
}
