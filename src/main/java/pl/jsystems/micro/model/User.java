package pl.jsystems.micro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//@ToString
//@Setter
//@Getter   // autometycznie generuje gettery
@Data       // automatycznie generuje gettery, settery i toString
@AllArgsConstructor     // automatycznie gentuje konstruktor w wszystkimi polami w argumentach
@NoArgsConstructor      // automatycznie generuje konstruktor bezparametrowy
@Entity     // klasa mapowana na tabelkę db
@Table(name = "users")
public class User {
    @Id                                                 // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INKREMENTACJA
//    @Column(name = "user_id")                         // tak jest domyślnie mapowany CS
    private long userId;
    @Column(unique = true)                              // UNIQUE
    private String email;
    private String password;
    @Column(name = "registration_time")
    private LocalDateTime registrationDateTime = LocalDateTime.now();
    private boolean status;
    @JsonIgnore         // wykluczenie pola z API w formacie JSON
    @Transient
    private String secret = "SECRET CODE";

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.DETACH    // CascaseType.DETACH, the child entity will also get removed from the persistent context
    )                       //REALCJA N:M
    @JoinTable(
            name = "users_to_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
