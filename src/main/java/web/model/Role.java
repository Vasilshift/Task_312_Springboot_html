package web.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.*;

//@EqualsAndHashCode(of = "name")
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority{

    public Role() { }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String name;

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
//    private Set<User> users;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name.substring(5);
    }

    @Override
    public String getAuthority() {
        return name;
    }

}
