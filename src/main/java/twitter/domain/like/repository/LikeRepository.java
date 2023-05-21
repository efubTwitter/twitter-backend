package twitter.domain.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import twitter.domain.like.domain.Like;
import twitter.domain.tweet.domain.Tweet;
import twitter.domain.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Boolean existsByUserAndTweet(User user, Tweet tweet);

    Optional<Like> findByUserAndTweet(User user, Tweet tweet);

    Long countByTweet(Tweet tweet);

    List<Like> findAllByTweet(Tweet tweet);
}
