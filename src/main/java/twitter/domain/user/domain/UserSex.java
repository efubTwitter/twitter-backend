package twitter.domain.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserSex {
    FEMALE("여성"),
    MALE("남성");

    private final String kor;
}
