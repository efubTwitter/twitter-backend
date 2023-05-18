package twitter.domain.tweet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import twitter.domain.tweet.domain.Tweet;
import twitter.domain.tweet.dto.TweetRequestDto;
import twitter.domain.tweet.dto.TweetResponseDto;
import twitter.domain.tweet.service.TweetService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class TweetController {

    private final TweetService tweetService;

    @PostMapping("/tweets")
    @ResponseStatus(value = HttpStatus.CREATED)
    public TweetResponseDto tweetAdd(@RequestBody @Valid TweetRequestDto requestDto) {
        Tweet tweet = tweetService.addTweet(requestDto);
        return new TweetResponseDto(tweet);
    }

    @GetMapping("/tweets/{tweetId}")
    @ResponseStatus(value = HttpStatus.OK)
    public TweetResponseDto tweetFind(@PathVariable Long tweetId) {
        Tweet tweet = tweetService.findTweet(tweetId);
        return new TweetResponseDto(tweet);
    }

    @DeleteMapping("/tweets/{tweetId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void tweetRemove(@PathVariable Long tweetId) {
        tweetService.removeTweet(tweetId);
    }

    @GetMapping("/home")
    @ResponseStatus(value = HttpStatus.OK)
    public List<TweetResponseDto> tweetListFind() {
        List<Tweet> tweetList = tweetService.findTweetList();
        List<TweetResponseDto> responseDtoList = new ArrayList<>();
        for (Tweet tweet : tweetList) {
            responseDtoList.add(new TweetResponseDto(tweet));
        }
        return responseDtoList;
    }
}
