package gardncl.whobrokeitlast.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Project class that holds all aspects of the project including:
 * 1) List of developers working on it.
 * 2) Build settings
 */

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String projectTitle;
    @OneToMany(targetEntity = Developer.class)
    private List developers;
    @OneToOne
    private Build build;

    public Long getId() {
        return id;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List developers) {
        this.developers = developers;
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }
}