package gardncl.whobrokeitlast.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Build settings for a single project.
 */

@Entity
public class Build {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Project project;

    @NotNull
    private Boolean emailOnPass;
    @NotNull
    private Boolean emailOnFailure;

    public Long getId() {
        return id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Boolean getEmailOnPass() {
        return emailOnPass;
    }

    public void setEmailOnPass(Boolean emailOnPass) {
        this.emailOnPass = emailOnPass;
    }

    public Boolean getEmailOnFailure() {
        return emailOnFailure;
    }

    public void setEmailOnFailure(Boolean emailOnFailure) {
        this.emailOnFailure = emailOnFailure;
    }
}
