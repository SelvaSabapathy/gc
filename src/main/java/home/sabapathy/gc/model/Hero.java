package home.sabapathy.gc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Aathar_Number")
    private long anum;

    @Column(name = "Real_Name")
    private String name;

    @Column(name = "Hero_Name")
    private String heroName;
}
