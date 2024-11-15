package dongguknuri.repository.board;

import dongguknuri.domain.User;
import dongguknuri.domain.board.Like;
import dongguknuri.domain.board.Post;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPost(User user, Post post);
    Optional<Like> findByUserAndPost(User user, Post post);
}
