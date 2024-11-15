package dongguknuri.controller;

import dongguknuri.dto.global.AuthenticationResponse;
import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreateUserDto;
import dongguknuri.dto.request.UserLoginDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseDto<?> registerUser(@RequestBody CreateUserDto createUserDto) {
        return ResponseDto.created(userService.createUser(createUserDto));
    }

    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody UserLoginDto userLoginDto) {
        String token = userService.login(userLoginDto).jwt();
        if (token != null) {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse(token);
            return ResponseDto.ok(userService.login(userLoginDto));
        } else {
            return ResponseDto.fail(new CommonException(ErrorCode.LOGIN_FAILURE));
        }
    }
}
