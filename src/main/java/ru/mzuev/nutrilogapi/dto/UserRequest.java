package ru.mzuev.nutrilogapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.mzuev.nutrilogapi.model.enums.TargetType;

@Data
public class UserRequest {

    @NotBlank(message = "{validation.user.name.blank}")
    @Size(min = 2, max = 50, message = "{validation.user.name.size}")
    private String name;

    @Email(message = "{validation.user.email.invalid}")
    private String email;

    @Min(value = 1, message = "{validation.user.age.min}")
    @Max(value = 150, message = "{validation.user.age.max}")
    private Integer age;

    @Positive(message = "{validation.user.weight.positive}")
    private Double weight;

    @Positive(message = "{validation.user.height.positive}")
    private Double height;

    @NotNull(message = "{validation.user.target.notnull}")
    private TargetType targetType;
}