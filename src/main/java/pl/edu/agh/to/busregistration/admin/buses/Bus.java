package pl.edu.agh.to.busregistration.admin.buses;

import lombok.Data;
import pl.edu.agh.to.busregistration.admin.passages.Passage;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "buses", uniqueConstraints = {@UniqueConstraint(columnNames = {"registration_number"})})
@Data
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "seats")
    @Min(8)
    private int seats;

    @Column(name = "assigned", nullable = false)
    private boolean assigned;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    private Passage passage;
}
