package pl.edu.agh.to.busregistration.admin.routes;


import lombok.Data;
import pl.edu.agh.to.busregistration.admin.passages.Passage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routes")
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "from_city")
    private String fromCity;

    @Column(name = "to_city")
    private String toCity;

    @Column(name = "duration")
    private int duration;

    @Column(name = "price")
    private double price;

    @Column(name = "assigned", nullable = false)
    private boolean assigned;

    @OneToMany(mappedBy = "route", orphanRemoval = true)
    private List<Passage> passages = new ArrayList<>();
}