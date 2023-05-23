package twitter.domain.follow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import twitter.domain.follow.domain.Follow;
import twitter.domain.follow.dto.FollowListResponseDto;
import twitter.domain.follow.dto.FollowRequestDto;
import twitter.domain.follow.repository.FollowRepository;
import twitter.domain.user.domain.User;
import twitter.domain.user.service.UserService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserService userService;

    public String addFollow(String userId, String followingId){
        User follower = userService.findUser(userId);
        User following = userService.findUser(followingId);

        if(followRepository.existsByFollowerAndFollowing(follower, following)) {
            deleteFollow(userId, followingId);
            return "팔로우를 취소하였습니다.";
        }

        Follow follow = followRepository.save(
                Follow.builder()
                        .follower(follower)
                        .following(following)
                        .build()
        );

        return "팔로우하였습니다.";
    }

    @Transactional(readOnly = true)
    public List<Follow> findFollowingList(String followerId){
        User follower = userService.findUser(followerId);
        return followRepository.findAllByFollower(follower);
    }

    @Transactional(readOnly = true)
    public List<Follow> findFollowerList(String followingId){
        User following = userService.findUser(followingId);
        return followRepository.findAllByFollowing(following);
    }

    public void deleteFollow(String userId, String followingId){
        User follower = userService.findUser(userId);
        User following = userService.findUser(followingId);

        Follow follow = followRepository.findByFollowerAndFollowing(follower, following);
        followRepository.deleteById(follow.getFollowId());
    }

    @Transactional(readOnly = true)
    public Boolean isFollowing(String followerId, String followingId){
        User follower = userService.findUser(followerId);
        User following = userService.findUser(followingId);
        return followRepository.existsByFollowerAndFollowing(follower, following);
    }

}
