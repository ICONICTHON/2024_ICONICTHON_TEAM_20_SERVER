package dongguknuri.service;

import dongguknuri.domain.Club;
import dongguknuri.domain.User;
import dongguknuri.domain.UserClub;
import dongguknuri.dto.request.DeleteClubDto;
import dongguknuri.dto.request.JoinClubDto;
import dongguknuri.dto.response.ClubResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.ClubRepository;
import dongguknuri.repository.UserClubRepository;
import dongguknuri.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserClubService {
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;
    private final UserClubRepository userClubRepository;

    @Transactional(readOnly = true)
    public List<ClubResponseDto> getAllClubs() {
        return clubRepository.findAll().stream()
                .map(club -> ClubResponseDto.of(
                        club.getClubId(),
                        club.getName(),
                        club.getDescription()
                )).collect(Collectors.toList());

    }

    @Transactional
    public boolean joinClub(JoinClubDto joinClubDto) {
        User user = userRepository.findById(joinClubDto.userId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Club club = clubRepository.findById(joinClubDto.clubId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CLUB));

        if (userClubRepository.existsByUserAndClub(user, club)) {
            throw new IllegalStateException("이미 가입된 클럽입니다.");
        }

        UserClub userClub = new UserClub(user, club);
        userClubRepository.save(userClub);
        return Boolean.TRUE;
    }

    @Transactional(readOnly = true)
    public List<ClubResponseDto> getUserClubs(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        return userClubRepository.findByUser(user).stream()
                .map(userClub -> ClubResponseDto.of(
                        userClub.getClub().getClubId(),
                        userClub.getClub().getName(),
                        userClub.getClub().getDescription()
                )).collect(Collectors.toList());
    }

    @Transactional
    public boolean leaveClub(DeleteClubDto deleteClubDto) {
        User user = userRepository.findById(deleteClubDto.userId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        Club club = clubRepository.findById(deleteClubDto.clubId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CLUB));

        UserClub userClub = userClubRepository.findByUserAndClub(user, club)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USERCLUB));

        userClubRepository.delete(userClub);
        return Boolean.TRUE;
    }
}
