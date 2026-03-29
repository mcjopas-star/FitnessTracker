package pl.wsb.fitnesstracker.health;

import jakarta.persistence.*;
import pl.wsb.fitnesstracker.user.api.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Health_Metrics") // dokładnie jak w schemacie
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @Column(name = "heartRate")
    private Integer heartRate;

    protected HealthMetrics() {
        // dla JPA
    }

    public HealthMetrics(
            final User user,
            final LocalDate date,
            final BigDecimal weight,
            final BigDecimal height,
            final Integer heartRate) {
        this.user = user;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }

    // gettery/settery (wygeneruj w IntelliJ)
}