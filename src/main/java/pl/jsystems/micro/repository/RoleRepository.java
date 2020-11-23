package pl.jsystems.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jsystems.micro.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
