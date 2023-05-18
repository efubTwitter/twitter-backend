package twitter.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import twitter.domain.user.domain.User;

import java.time.LocalDateTime;

/*
{
    "profilePhoto": "",
    "headerPhoto": "",
    "name": "퍼비",
    "bio" : "안녕하세요!",
    "joinedDate": "2021-07-24T01:33:40:000000"
}
 */

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String profilePhoto;
    private String headerPhoto;
    private String name;
    private String bio;
    private LocalDateTime joinedDate;

    public UserResponseDto(User user){
        this.profilePhoto = user.getProfilePhoto();
        this.headerPhoto= user.getHeaderPhoto();
        this.name= user.getName();
        this.bio= user.getBio();
        this.joinedDate=user.getCreatedDate();
    }
}
