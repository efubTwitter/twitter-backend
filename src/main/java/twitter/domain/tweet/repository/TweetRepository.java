package twitter.domain.tweet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import twitter.domain.tweet.domain.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
