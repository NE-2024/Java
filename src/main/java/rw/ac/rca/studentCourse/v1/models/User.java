package rw.ac.rca.studentCourse.v1.models;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID user_id;

    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String national_id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

    @Column
    private Date lastLogin;

    @Column
    @NotNull
    private String gender;

    @Column
    @NotNull
    private String profilePicture;

    @NotNull
    private String password;

    public User(String email, String username, String national_id, String profilePicture ,String password) {
        this.email = email;
        this.username = username;
        this.national_id = national_id;
        this.password = password;
        this.profilePicture = profilePicture;
    }
}