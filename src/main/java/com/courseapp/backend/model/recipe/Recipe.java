package com.courseapp.backend.model.recipe;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @NotNull
    @Pattern(regexp = "[a-zA-Z]+")
    private String name;
    @NotBlank
    private String description;
    private String imagePath;

}
