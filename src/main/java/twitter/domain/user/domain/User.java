package twitter.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import twitter.domain.heart.domain.Heart;
import twitter.global.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id", updatable = false, length = 30)
    private String userId;

    @Column(nullable = false, length = 20)
    private String password;

    @Column
    private String profilePhoto;

    @Column
    private String headerPhoto;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(length = 100)
    private String bio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> heartList = new ArrayList<>();

    @Builder
    public User(String userId, String password, String profilePhoto,
                String headerPhoto, String name, String bio) {
        this.userId = userId;
        this.password = password;
        this.profilePhoto = profilePhoto;
        this.headerPhoto = headerPhoto;
        this.name = name;
        this.bio = bio;
    }

}
