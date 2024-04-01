package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Setter
@Getter
public class GuestCreateDTO {

    @NotBlank(message = "Имя гостя не может быть пустым")
    private String name;

    @NotBlank(message = "Электронная почта гостя не может быть пустой")
    @Email(message = "Электронная почта гостя должна быть валидной")
    private String email;

    @NotBlank(message = "Номер телефона гостя не может быть пустым")
    @Pattern(regexp="\\+\\d{11,13}", message="Номер телефона должен начинаться с символа + и содержать от 11 до 13 цифр")
    private String phoneNumber;

    @NotBlank(message = "Номер клубной карты гостя не может быть пустым")
    @Pattern(regexp="\\d{4}", message="Номер клубной карты должен состоять ровно из четырех цифр")
    private String clubCard;

    @NotBlank(message = "Срок действия клубной карты гостя не может быть пустым")
    private LocalDate cardValidUntil;
}
// END
