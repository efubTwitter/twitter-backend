package twitter.domain.follow.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import twitter.domain.follow.domain.Follow;
import twitter.domain.follow.dto.FollowRequestDto;
import twitter.domain.follow.service.FollowService;
import twitter.domain.user.dto.UserResponseDto;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/follows/{userId}")
public class FollowController {
    private final FollowService followService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public String followClick(@PathVariable final String userId, @RequestBody final FollowRequestDto requestDto) {
        return followService.clickFollow(userId, requestDto.getFollowingId());
    }

    // 팔로워 목록 조회
    @GetMapping("/followers")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserResponseDto> followerList(@PathVariable final String userId) {
        List<Follow> followList = followService.findFollowerList(userId);
        List<UserResponseDto> responseDtoList = new ArrayList<>();
        for (Follow follow : followList) {
            responseDtoList.add(
                    new UserResponseDto(follow.getFollower())
            );
        }
        return responseDtoList;
    }

    // 팔로잉 목록 조회
    @GetMapping("/followings")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserResponseDto> followingList(@PathVariable final String userId) {
        List<Follow> followList = followService.findFollowingList(userId);
        List<UserResponseDto> responseDtoList = new ArrayList<>();
        for (Follow follow : followList) {
            responseDtoList.add(
                    new UserResponseDto(follow.getFollowing())
            );
        }
        return responseDtoList;
    }
}
