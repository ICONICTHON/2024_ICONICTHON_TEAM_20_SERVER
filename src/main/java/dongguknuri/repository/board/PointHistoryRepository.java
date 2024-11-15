package dongguknuri.repository.board;

import dongguknuri.domain.User;
import dongguknuri.domain.board.PointHistory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
    List<PointHistory> findByUser(User user);
}
