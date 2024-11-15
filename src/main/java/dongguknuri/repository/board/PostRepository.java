package dongguknuri.repository.board;

import dongguknuri.domain.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
