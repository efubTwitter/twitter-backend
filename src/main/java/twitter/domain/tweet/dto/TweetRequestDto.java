package twitter.domain.tweet.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
{
		"writerId" : "mapo_nongsusan",
		"content" : "밥 챙겨먹어요..행복하세요,.저도 행복할게요.."
}
 */

@Getter
@NoArgsConstructor
public class TweetRequestDto {
    private String writerId;
    private String content;
}
