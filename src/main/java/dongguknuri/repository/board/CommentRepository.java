package dongguknuri.repository.board;

import dongguknuri.domain.board.Comment;
import dongguknuri.domain.board.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
