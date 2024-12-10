package com.it.majesticcup.models.collections;



import com.it.majesticcup.models.subdocuments.Result;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "matches")
public class Match {
    @Id
    private String id;
    @NotNull
    private int round;

    @NotBlank
    private String team1;

    @NotBlank
    private String team2;

    private Result result;

    @NotBlank
    private String winner;
}
