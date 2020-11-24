package pl.jsystems.micro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.hateoas.RepresentationModel;
import pl.jsystems.micro.model.enums.Category;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts", indexes = @Index(name = "pdt", columnList = "publicationDateTime"))
@Entity
public class Post extends RepresentationModel<Post> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;
    private String title;
    @Type(type = "text")    // mapuje typ kolumny w tabelce db na longtext
    private String content;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private LocalDateTime publicationDateTime = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;
}
