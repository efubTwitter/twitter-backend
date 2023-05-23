package twitter.domain.follow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import twitter.domain.follow.domain.Follow;
import twitter.domain.user.domain.User;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // follower가 following을 follow하고 있는지 확인
    Boolean existsByFollowerAndFollowing(User follower, User following);

    List<Follow> findAllByFollower(User follower);

    List<Follow> findAllByFollowing(User following);

    Follow findByFollowerAndFollowing(User follower, User following);
}
