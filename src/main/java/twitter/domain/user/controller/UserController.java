package twitter.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import twitter.domain.user.domain.User;
import twitter.domain.user.dto.UserResponseDto;
import twitter.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponseDto profileShow(@PathVariable String userId){
        User user = userService.findUser(userId);
        return new UserResponseDto(user);
    }
}
