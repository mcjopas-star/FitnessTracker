package pl.wsb.fitnesstracker.health;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import pl.wsb.fitnesstracker.user.api.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "health_metrics")
public class HealthMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "weight", precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(name = "height", precision = 5, scale = 2)
    private BigDecimal height;

    @Column(name = "heartrate")
    private Integer heartrate;

    public HealthMetric(
            final User user,
            final LocalDate date,
            final BigDecimal weight,
            final BigDecimal height,
            final Integer heartrate) {

        this.user = user;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartrate = heartrate;
    }

    protected HealthMetric() {
        // dla JPA
    }

    // gettery/settery (wygeneruj w IntelliJ)
}
