package twitter.domain.heart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import twitter.domain.heart.dto.HeartRequestDto;
import twitter.domain.heart.dto.HeartResponseDto;
import twitter.domain.heart.service.HeartService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/hearts")
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public HeartResponseDto heartClick(@PathVariable String userId, @RequestBody @Valid HeartRequestDto requestDto){
        heartService.clickHeart(userId, requestDto.getTweetId());

        if(heartService.isHeart(userId, requestDto.getTweetId()))
            return new HeartResponseDto(userId, requestDto.getTweetId(), true);
        else
            return new HeartResponseDto(userId, requestDto.getTweetId(), false);
    }

    @DeleteMapping("/{tweetId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void heartRemove(@PathVariable String userId, @PathVariable Long tweetId){
        heartService.removeHeart(userId, tweetId);
    }
}
