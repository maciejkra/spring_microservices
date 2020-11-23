package pl.jsystems.micro.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime registrationDateTime;
    private boolean status;
    @Transient
    private String secret = "SECRET CODE";


}
