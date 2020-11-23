package pl.jsystems.micro.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jsystems.micro.model.User;

@Repository         // interfejs o zanczeniu specjalnym implementujący metody mapujące zapytania SQL
                                    // JpaReposiroty<KlasaModelu, TypKluczaGłównego>
public interface UserRepository extends JpaRepository<User, Long> {
}
