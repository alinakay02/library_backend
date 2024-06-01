package uz.library.takm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

    private static final long serialVersionUID = 6280397593382450688L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LIB_ROLE")
    @SequenceGenerator(name = "SEQ_LIB_ROLE", sequenceName = "SEQ_LIB_ROLE", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME", length = 32, unique = true, nullable = false)
    private RoleName roleName;

}
