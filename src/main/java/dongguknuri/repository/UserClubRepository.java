package dongguknuri.repository;

import dongguknuri.domain.Club;
import dongguknuri.domain.User;
import dongguknuri.domain.UserClub;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClubRepository extends JpaRepository<UserClub, Long>{
    boolean existsByUserAndClub(User user, Club club);
    List<UserClub> findByUser(User user);
    Optional<UserClub> findByUserAndClub(User user, Club club);
}
