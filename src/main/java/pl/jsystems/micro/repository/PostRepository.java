package pl.jsystems.micro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jsystems.micro.model.Post;
import pl.jsystems.micro.model.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Ile jest postów poszczególnych kategorii
    @Query(
            value = "SELECT p.category, count(*) FROM posts p GROUP BY p.category ORDER BY 2 DESC",
            nativeQuery = true
    )
    List<Object[]> getCategoryStatistics();

    Page<Post> findAll(Pageable pageable);
    List<Post> findAllByAuthor(User author);
    // Przypisanie postów z null w miejscy autora do wybranego administratora
    @Modifying
    @Query(value = "UPDATE Post p SET p.author = :admin WHERE p.author is null")
    @Transactional
    void updateRemovedAuthorPosts(@Param("admin") User admin);

}
