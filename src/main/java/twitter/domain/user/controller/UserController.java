package twitter.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import twitter.domain.heart.domain.Heart;
import twitter.domain.heart.service.HeartService;
import twitter.domain.tweet.domain.Tweet;
import twitter.domain.tweet.dto.TweetResponseDto;
import twitter.domain.tweet.service.TweetService;
import twitter.domain.user.domain.User;
import twitter.domain.user.dto.UserResponseDto;
import twitter.domain.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final HeartService heartService;
    private final TweetService tweetService;

    @GetMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponseDto profileShow(@PathVariable String userId) {
        User user = userService.findUser(userId);
        return new UserResponseDto(user);
    }

    @GetMapping("/{userId}/heart_tweets")
    @ResponseStatus(value = HttpStatus.OK)
    public List<TweetResponseDto> HeartTweetsFind(@PathVariable String userId) {
        // 유저가 누른 마음 리스트 추출
        List<Heart> heartUserList = heartService.findHeartListByUser(userId);

        // 마음 엔티티가 가지고 있는 트윗을 추출하여 트윗 리스트에 저장
        List<Tweet> heartTweetList = new ArrayList<>();
        for (Heart heart : heartUserList) {
            heartTweetList.add(
                    tweetService.findTweet(
                            heart.getTweet().getTweetId()
                    )
            );
        }

        // 트윗 리스트에서 응답 리스트 만들기
        List<TweetResponseDto> tweetResponseDtoList = new ArrayList<>();
        for (Tweet tweet : heartTweetList) {
            tweetResponseDtoList.add(
                    new TweetResponseDto(tweet)
            );
        }

        return tweetResponseDtoList;
    }
}
