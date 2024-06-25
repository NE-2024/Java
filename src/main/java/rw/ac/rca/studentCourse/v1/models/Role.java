package rw.ac.rca.studentCourse.v1.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import rw.ac.rca.studentCourse.v1.enums.Roles;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    public Long roleId;
    @Column
    @NotNull
    public String roleName;

    public Role(Roles roleName) {
    }
}
