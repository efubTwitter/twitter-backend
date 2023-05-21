package twitter.domain.heart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import twitter.domain.heart.domain.Heart;
import twitter.domain.tweet.domain.Tweet;
import twitter.domain.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Boolean existsByUserAndTweet(User user, Tweet tweet);

    Optional<Heart> findByUserAndTweet(User user, Tweet tweet);

    Long countByTweet(Tweet tweet);

    List<Heart> findAllByTweet(Tweet tweet);
}
