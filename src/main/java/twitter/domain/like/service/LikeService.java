package twitter.domain.like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twitter.domain.like.domain.Like;
import twitter.domain.like.repository.LikeRepository;
import twitter.domain.tweet.domain.Tweet;
import twitter.domain.tweet.repository.TweetRepository;
import twitter.domain.tweet.service.TweetService;
import twitter.domain.user.domain.User;
import twitter.domain.user.repository.UserRepository;
import twitter.domain.user.service.UserService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final TweetService tweetService;
    private final UserService userService;

    public void addLike(String userId, Long tweetId){
        User user = userService.findUser(userId);
        Tweet tweet = tweetService.findTweet(tweetId);

        // 한 유저가 한 게시물에 대해 한 번만 마음을 누를 수 있도록 제한
        if(likeRepository.existsByUserAndTweet(user, tweet)){
            removeLike(userId, tweetId);
            return;
        }

        Like like = likeRepository.save(Like.builder()
                .user(user)
                .tweet(tweet)
                .build()
        );

        tweet.updateLike(like);
    }

    public List<Like> findLikeList(Long tweetId){
        Tweet tweet = tweetService.findTweet(tweetId);
        return likeRepository.findAllByTweet(tweet);
    }

    public void removeLike(String userId, Long tweetId){
        User user = userService.findUser(userId);
        Tweet tweet = tweetService.findTweet(tweetId);

        Like like=likeRepository.findByUserAndTweet(user, tweet)
                .orElseThrow(()->new RuntimeException("마음을 누르지 않았습니다."));
        likeRepository.delete(like);
    }

    @Transactional(readOnly = true)
    public Long countLike(Tweet tweet){
        return likeRepository.countByTweet(tweet);
    }
}
