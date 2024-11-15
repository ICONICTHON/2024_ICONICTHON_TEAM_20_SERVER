package dongguknuri.service;

import dongguknuri.component.JwtTokenService;
import dongguknuri.domain.Department;
import dongguknuri.domain.User;
import dongguknuri.dto.global.JwtResponseDto;
import dongguknuri.dto.request.CreateUserDto;
import dongguknuri.dto.request.UserLoginDto;
import dongguknuri.dto.response.UserResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.DepartmentRepository;
import dongguknuri.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final JwtTokenService jwtTokenService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UserResponseDto createUser(CreateUserDto createUserDto) {
        String encodedPassword = passwordEncoder.encode(createUserDto.password());
        Department department = departmentRepository.findById(createUserDto.departmentId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));

        User save = userRepository.save(User.builder()
                .name(createUserDto.name())
                .email(createUserDto.email())
                .password(encodedPassword)
                .department(department)
                .mbti(createUserDto.mbti())
                .personality(createUserDto.personality())
                .build()
        );

        return UserResponseDto.of(
                save.getName(),
                save.getEmail()
        );
    }

    @Transactional
    public JwtResponseDto login(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.email())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        if (user != null && passwordEncoder.matches(userLoginDto.password(), user.getPassword())) {
            String jwt = jwtTokenService.generateToken(user.getEmail());
            return JwtResponseDto.builder()
                    .jwt(jwt)
                    .name(user.getEmail())
                    .build();
        }
        return JwtResponseDto.builder()
                .jwt(null)
                .name(null)
                .build();
    }
}
