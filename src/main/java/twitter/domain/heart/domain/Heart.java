package twitter.domain.heart.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import twitter.domain.tweet.domain.Tweet;
import twitter.domain.user.domain.User;
import twitter.global.entity.BaseTimeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Heart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_id")
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "tweet_id", updatable = false)
    private Tweet tweet;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @Builder
    public Heart(Tweet tweet, User user) {
        this.tweet = tweet;
        this.user = user;
    }
}
