package pl.jsystems.micro.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.jsystems.micro.model.enums.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class PostDto {
    @NotBlank(message = "Tytuł nie może być puste")
    @Size(min = 1, max = 255, message = "Tytuł musi zawierać co najmniej {min} a maksymalnie {max} znaków")
    private String title;
    @NotBlank(message = "Zawartość nie może być pusta")
    @Size(min = 10, message = "Zawartość posta musi zawierać co najmniej {min}")
    private String content;
//    @NotBlank(message = "Pole kategoria nie może być puste")
    private Category category;
    private long authorId;
}
