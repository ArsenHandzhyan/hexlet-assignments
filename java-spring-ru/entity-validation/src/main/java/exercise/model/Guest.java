package exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;




@Entity
@Table(name = "guests")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

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

    @Future(message = "Срок действия клубной карты должен быть еще не истекшим")
    private LocalDate cardValidUntil;

    @CreatedDate
    private LocalDate createdAt;
}
