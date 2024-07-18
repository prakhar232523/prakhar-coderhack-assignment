package com.crio.coderhack.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScoreUpdateDto {
    @Max(value = 100, message = "Score must be less than or equal to 100")
    @Min(value = 0, message = "Score must be greater than or equal to 0")
    @NotNull(message = "Score must not be null and no fields other than score can be updated!")
    private Integer score;
}
