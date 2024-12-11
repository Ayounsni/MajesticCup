package com.it.majesticcup.models.dtos.MatchDTO;

import com.it.majesticcup.models.subdocuments.Result;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMatchDTO {

    private Result result;


    private String winner;
}
