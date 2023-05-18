package twitter.domain.tweet.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import twitter.domain.tweet.domain.Tweet;

import java.time.LocalDateTime;

/*
{
		"writerPhoto" : "",
		"writerName" : "마포농수산쎈타",
		"writerId" : "mapo_nongsusan",
        "content" : "밥 챙겨먹어요..행복하세요,.저도 행복할게요..",
		"createdDate" : "2023-05-16T22:48:33:000000",
		"tweetId" : 1
}
 */
@Getter
@NoArgsConstructor
public class TweetResponseDto {
    private String writerPhoto;
    private String writerName;
    private String writerId;
    private String content;
    private LocalDateTime createdDate;
    private Long id;

    public TweetResponseDto(Tweet tweet) {
        this.writerPhoto = tweet.getWriter().getProfilePhoto();
        this.writerName = tweet.getWriter().getName();
        this.writerId = tweet.getWriter().getUserId();
        this.content = tweet.getContent();
        this.createdDate = tweet.getCreatedDate();
        this.id = tweet.getTweetId();
    }
}
