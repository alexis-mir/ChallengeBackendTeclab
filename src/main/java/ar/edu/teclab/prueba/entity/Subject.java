package ar.edu.teclab.prueba.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Alexis
 */
@Entity
@Getter
@Setter
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE subject SET deleted = true WHERE id=?")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "career_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "career_id"))
    private List<Career> careers;
    private LocalDateTime createAt;
    private boolean deleted;
    private LocalDateTime deletedAt;
}
