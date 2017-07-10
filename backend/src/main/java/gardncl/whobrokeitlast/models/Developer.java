package gardncl.whobrokeitlast.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;


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
    private Integer numberOfBrokenBuilds=0;
    @OneToMany
    private List<Project> projects;
    @Email
    private String email;

    public Developer() {
    }

    public Developer(String userName,
                     String email,
                     Project project) {
        this.userName = userName;
        this.email = email;
        this.projects = asList(project);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public Date getLastBreak() {
        return lastBreak;
    }

    public void setLastBreak(Date lastBreak) {
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
