package pl.edu.agh.to.busregistration.admin.passages;

import lombok.Data;
import pl.edu.agh.to.busregistration.admin.buses.Bus;
import pl.edu.agh.to.busregistration.admin.routes.Route;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "passages")
public class Passage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date", columnDefinition = "TIMESTAMP")
    private Date date;

    @Column(name = "to")
    private String to;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "passage", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passage_id")
    private Route route;
}
