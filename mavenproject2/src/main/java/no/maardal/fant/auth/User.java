package no.maardal.fant.auth;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import static no.maardal.fant.auth.User.FIND_ALL_USERS;
import static no.maardal.fant.auth.User.FIND_USER_BY_IDS;

/**
 * Represents an user in the Fant webstore
 * @author Martin
 */

@Entity
@Table(name = "AUSER")
@Data @AllArgsConstructor @NoArgsConstructor
@NamedQuery(name = FIND_ALL_USERS, query = "select u from User u order by u.firstName")
@NamedQuery(name = FIND_USER_BY_IDS, query = "select u from User u where u.userid in :ids")
public class User implements Serializable{
    public static final String FIND_ALL_USERS = "User.findAllUsers";
    public static final String FIND_USER_BY_IDS = "User.findAllUsers";
    
    public enum State {
        ACTIVE, INACTIVE
    }
    
    @Id
    @Column(name = "userid")
    private long userId;
    
    @Version
    Timestamp version;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    Date created;
    
    @Enumerated(EnumType.STRING)
    State currentState = State.ACTIVE;
   
    @Email
    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    
    @JsonbTransient
    @NotNull
    @Column(name = "user_password", nullable = false)
    private String password;
    
    @NotNull
    @Column(name = "firstName", nullable = false)
    private String firstName;
    
    @NotNull
    @Column(name = "lastName", nullable = false)
    private String lastName;
    
    @ManyToMany
    @JoinTable(name="AUSERGROUP",
            joinColumns = @JoinColumn(name="userid", referencedColumnName = "userid"),
            inverseJoinColumns = @JoinColumn(name="name", referencedColumnName = "name"))
    @Column(name = "user_group", nullable = false)
    List<Group> groups;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
    
    public List<Group> getGroups() {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        return groups;
    }
}
