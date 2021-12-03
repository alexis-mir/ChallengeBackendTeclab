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
@Setter
@Getter
@ToString
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE subject SET deleted = true WHERE id=?")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String title;
    @ManyToMany(mappedBy = "careers")
    private List<Subject> subjects;
    private LocalDateTime createAt;
    private boolean deleted;
    private LocalDateTime deletedAt;
}
