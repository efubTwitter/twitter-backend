package twitter.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twitter.domain.user.domain.User;
import twitter.domain.user.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));
    }
}
