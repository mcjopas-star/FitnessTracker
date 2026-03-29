package pl.wsb.fitnesstracker.statistics.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

@Entity
@Table(name = "Statistics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(optional = false)  // jednostronne OneToOne -> User
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "totalTrainings", nullable = false)
    private Integer totalTrainings;

    @Column(name = "totalDistance", nullable = false)
    private Double totalDistance;

    @Column(name = "totalCaloriesBurned", nullable = false)
    private Integer totalCaloriesBurned;

    public Statistics(User user,
                      Integer totalTrainings,
                      Double totalDistance,
                      Integer totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}