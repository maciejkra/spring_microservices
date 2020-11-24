package pl.jsystems.micro.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role extends RepresentationModel<Role> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    private String roleName;
}
