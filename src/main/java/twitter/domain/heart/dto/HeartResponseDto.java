package twitter.domain.heart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HeartResponseDto {
    private String userId;
    private Long tweetId;
    private Boolean isClicked;

    @Builder
    public HeartResponseDto(String userId, Long tweetId, Boolean isClicked){
        this.userId = userId;
        this.tweetId = tweetId;
        this.isClicked = isClicked;
    }
}
