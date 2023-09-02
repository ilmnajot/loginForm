package uz.ilmnajot.loginform.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import uz.ilmnajot.loginform.enums.Role;
import uz.ilmnajot.loginform.model.User;

@Data
public class UserDto {

    private String name;
    private String username;
    private String email;
    private int age;
    private Role role;

    public static UserDto toDto(User user){
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        dto.setRole(user.getRole());
        return dto;
    }
}
