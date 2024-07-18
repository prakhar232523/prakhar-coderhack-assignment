package com.crio.coderhack.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {

    @NotBlank
    @Id
    private String userId;

    @NotBlank
    private String userName;

    @Max(value = 100, message = "Score must be between 0 and 100")
    @Min(value = 0, message = "Score must be between 0 and 100")
    private Integer score;

    private Set<Badge> badges;

    public User(@NotBlank String userId, @NotBlank String userName){
        this.userId = userId;
        this.userName = userName;
        this.score = 0;
        this.badges = new HashSet<>();
    }

}
