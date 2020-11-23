package pl.jsystems.micro.model.dtos;

import pl.jsystems.micro.model.enums.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostDto {
    @Size(min = 1, max = 255, message = "Tytuł musi zawierać co najmniej {min} a maksymalnie {max} znaków")
    private String title;
    @Size(min = 10, message = "Zawartość posta musi zawierać co najmniej {min}")
    private String content;
    @NotBlank(message = "Pole kategoria nie może być puste")
    private Category category;
    @NotBlank(message = "pole autora posta nie może być puste")
    private long authorId;
}
