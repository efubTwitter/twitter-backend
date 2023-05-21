package twitter.domain.tweet.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import twitter.domain.heart.domain.Heart;
import twitter.domain.user.domain.User;
import twitter.global.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tweet extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id", updatable = false)
    private Long tweetId;

    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Column(nullable = false, length = 140)
    private String content;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> heartList = new ArrayList<>();

    @Builder
    public Tweet(User writer, String content) {
        this.writer = writer;
        this.content = content;
    }

    public void updateHeart(Heart heart){
        this.heartList.add(heart);
    }
}
