package uz.ilmnajot.loginform.dto;
import lombok.Data;

@Data
public class UserForm {

    private String name;
    private String username;
    private String email;
    private String password;
    private int age;
}
