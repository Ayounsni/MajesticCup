package com.it.majesticcup.security.entities;



import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "appusers")
public class AppUser {

    @Id
    private String id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;


    private boolean enabled = true;


    private String role;
}


