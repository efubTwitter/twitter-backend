package twitter.domain.like.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class LikeRequestDto {
    @NotNull
    private Long tweetId;
}
