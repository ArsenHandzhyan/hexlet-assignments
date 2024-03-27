package exercise.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
public class GuestCreateDTO {

    @NotBlank(message = "Name must not be empty")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be empty")
    private String email;

    @Pattern(regexp = "^\\+\\d{11,13}$", message = "Phone number must start with '+' and contain 11 to 13 digits")
    private String phoneNumber;

    @Pattern(regexp = "^\\d{4}$", message = "Club card must consist of exactly four digits")
    private String clubCard;

    @FutureOrPresent(message = "Club card valid until date must be in the present or future")
    private LocalDate cardValidUntil;
}
