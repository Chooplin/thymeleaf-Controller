package org.example.viewcontroller;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dto {

    private String name;

    @Email(message = "이메일형식이 아님")
    private String email;
}
