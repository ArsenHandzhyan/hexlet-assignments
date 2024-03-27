package exercise.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import exercise.dto.GuestCreateDTO;
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
