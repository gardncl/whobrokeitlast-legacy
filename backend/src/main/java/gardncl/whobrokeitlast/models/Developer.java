package gardncl.whobrokeitlast.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


/**
 * Developer class that stores the developers that are on each project.
 * This is not a user object.
 */

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"userName", "email"})})
public class Developer implements Comparable<Developer> {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String userName;
    private String firstName;
    private String lastName;
    private Date lastBreak;
    private Integer numberOfBrokenBuilds;
    @OneToMany(targetEntity = Project.class)
    private List projects;
    @Email
    private String email;

    public Developer() {
    }

    public Developer(String userName,
                     String email) {
        this.userName = userName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public Date getLastBreak() {
        return lastBreak;
    }

    public void setLastBreak(Date lastBreak) {
        if (numberOfBrokenBuilds == null) {
            numberOfBrokenBuilds = 0;
        }
        ++numberOfBrokenBuilds;
        this.lastBreak = lastBreak;
    }

    public Integer getNumberOfBrokenBuilds() {
        return numberOfBrokenBuilds;
    }

    public void setNumberOfBrokenBuilds(Integer numberOfBrokenBuilds) {
        this.numberOfBrokenBuilds = numberOfBrokenBuilds;
    }

    @Override
    public int compareTo(Developer o) {
        return this.getLastBreak().compareTo(o.getLastBreak());
    }
}
