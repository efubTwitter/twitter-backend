package twitter.domain.tweet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twitter.domain.tweet.domain.Tweet;
import twitter.domain.tweet.dto.TweetRequestDto;
import twitter.domain.tweet.repository.TweetRepository;
import twitter.domain.user.domain.User;
import twitter.domain.user.repository.UserRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public Tweet addTweet(TweetRequestDto requestDto) {
        User writer = userRepository.findById(requestDto.getWriterId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));

        return tweetRepository.save(
                Tweet.builder()
                        .writer(writer)
                        .content(requestDto.getContent())
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public Tweet findTweet(Long tweetId) {
        return tweetRepository.findById(tweetId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 트윗입니다."));
    }

    @Transactional(readOnly = true)
    public List<Tweet> findTweetList(){
        return tweetRepository.findAll();
    }

    public void removeTweet(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 트윗입니다."));
        tweetRepository.delete(tweet);
    }
}
