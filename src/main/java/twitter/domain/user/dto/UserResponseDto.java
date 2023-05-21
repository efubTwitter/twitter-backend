package twitter.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import twitter.domain.user.domain.User;

import java.time.LocalDateTime;

/*
{
	"userId" : "chlove_u",
    "profilePhoto": "",
    "headerPhoto": "",
    "name": "문화재청",
    "bio" : "경산토기를 쓴 문화재청 공식 트위터입니다.",
    "joinedDate": "2011-03-24T01:33:40:000000"
}
 */

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String userId;
    private String profilePhoto;
    private String headerPhoto;
    private String name;
    private String bio;
    private LocalDateTime joinedDate;

    public UserResponseDto(User user){
        this.userId = user.getUserId();
        this.profilePhoto = user.getProfilePhoto();
        this.headerPhoto= user.getHeaderPhoto();
        this.name= user.getName();
        this.bio= user.getBio();
        this.joinedDate=user.getCreatedDate();
    }
}
