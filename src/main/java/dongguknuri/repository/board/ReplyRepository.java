package dongguknuri.repository.board;

import dongguknuri.domain.board.Comment;
import dongguknuri.domain.board.Reply;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByComment(Comment comment);
}
