package twitter.domain.heart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twitter.domain.heart.domain.Heart;
import twitter.domain.heart.repository.HeartRepository;
import twitter.domain.tweet.domain.Tweet;
import twitter.domain.tweet.service.TweetService;
import twitter.domain.user.domain.User;
import twitter.domain.user.service.UserService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final TweetService tweetService;
    private final UserService userService;

    public void addHeart(String userId, Long tweetId){
        User user = userService.findUser(userId);
        Tweet tweet = tweetService.findTweet(tweetId);

        // 한 유저가 한 게시물에 대해 한 번만 마음을 누를 수 있도록 제한
        if(heartRepository.existsByUserAndTweet(user, tweet)){
            removeHeart(userId, tweetId);
            return;
        }

        Heart heart = heartRepository.save(
                Heart.builder()
                .user(user)
                .tweet(tweet)
                .build()
        );

        tweet.addHeart(heart);
    }

    public List<Heart> findHeartListByUser(String userId){
        User user = userService.findUser(userId);
        return heartRepository.findAllByUser(user);
    }

    public void removeHeart(String userId, Long tweetId){
        User user = userService.findUser(userId);
        Tweet tweet = tweetService.findTweet(tweetId);

        Heart heart=heartRepository.findByUserAndTweet(user, tweet)
                .orElseThrow(()->new RuntimeException("마음을 누르지 않았습니다."));

        tweet.deleteHeart(heart);

        heartRepository.delete(heart);
    }
}
