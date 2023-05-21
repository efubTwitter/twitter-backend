package twitter.domain.tweet.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import twitter.domain.heart.domain.Heart;
import twitter.domain.tweet.domain.Tweet;
import twitter.domain.user.dto.UserResponseDto;

import java.time.LocalDateTime;
import java.util.List;

/*
{
		"writer" : {
			"id" : "chlove_u",
            "profilePhoto": "",
            "headerPhoto": "",
            "name": "문화재청",
            "bio" : "경산토기를 쓴 문화재청 공식 트위터입니다.",
            "joinedDate": "2011-03-24T01:33:40:000000"
		},
		"content" : "첫 트윗",
		"createdDate" : "2023-05-16T22:48:33:000000",
		"tweetId" : 1
}
 */
@Getter
@NoArgsConstructor
public class TweetResponseDto {
    private Long tweetId;
    private UserResponseDto writer;
    private String content;
    private LocalDateTime createdDate;
    private Integer heartCount;

    public TweetResponseDto(Tweet tweet) {
        this.tweetId = tweet.getTweetId();
        this.writer = new UserResponseDto(tweet.getWriter());
        this.content = tweet.getContent();
        this.createdDate = tweet.getCreatedDate();
        this.heartCount = tweet.getHeartList().size();
    }
}
