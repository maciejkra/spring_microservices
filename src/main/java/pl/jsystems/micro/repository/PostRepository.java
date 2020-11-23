package pl.jsystems.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.jsystems.micro.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Ile jest postów poszczególnych kategorii
    @Query(
            value = "SELECT p.category, count(*) FROM posts p GROUP BY p.category ORDER BY 2 DESC",
            nativeQuery = true
    )
    List<Object[]> getCategoryStatistics();

}
