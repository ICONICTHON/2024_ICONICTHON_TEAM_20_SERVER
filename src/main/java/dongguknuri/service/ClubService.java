package dongguknuri.service;

import dongguknuri.domain.Club;
import dongguknuri.dto.request.CreateClubDto;
import dongguknuri.dto.response.ClubResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.ClubRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    @Transactional(readOnly = true)
    public List<ClubResponseDto> getAllClubs() {
        return clubRepository.findAll().stream()
                .map(club -> ClubResponseDto.of(
                        club.getClubId(),
                        club.getName(),
                        club.getDescription()
                )).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClubResponseDto getClubById(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CLUB));

        return ClubResponseDto.builder()
                .clubId(club.getClubId())
                .name(club.getName())
                .description(club.getDescription())
                .build();
    }

    @Transactional
    public boolean createClub(CreateClubDto createClubDto) {
        clubRepository.save(Club.builder()
                .name(createClubDto.name())
                .description(createClubDto.description())
                .build());
        return Boolean.TRUE;
    }

    @Transactional
    public boolean deleteClub(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CLUB));

        clubRepository.delete(club);
        return Boolean.TRUE;
    }
}
