package twitter.domain.like.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import twitter.domain.like.dto.LikeRequestDto;
import twitter.domain.like.service.LikeService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void likeAdd(@PathVariable String userId, @RequestBody @Valid LikeRequestDto requestDto){
        likeService.addLike(userId, requestDto.getTweetId());
    }

    @DeleteMapping("/{tweetId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void likeRemove(@PathVariable String userId, @PathVariable Long tweetId){
        likeService.removeLike(userId, tweetId);
    }
}
