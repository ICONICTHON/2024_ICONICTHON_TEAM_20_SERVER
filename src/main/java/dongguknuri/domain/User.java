package dongguknuri.domain;

import dongguknuri.enums.Departments;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "mbti")
    private String mbti;

    @Column(name = "personality")
    private String personality;

    @Column(name = "points")
    private int points;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserClub> userClubs = new ArrayList<>();

    @Builder
    public User(String name, String email, String password, Department department, String mbti, String personality) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
        this.mbti = mbti;
        this.personality = personality;
        this.points = 0;
    }

    public void addClub(Club club) {
        UserClub userClub = new UserClub(this, club);
        userClubs.add(userClub);
        club.getUserClubs().add(userClub);
    }

    private void addPoint(int point) {
        this.points += point;
    }
}
