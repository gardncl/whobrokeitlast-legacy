package gardncl.whobrokeitlast.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Break implements Comparable<Break> {

    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    @OneToOne
    public Developer developer;

    @NotNull
    @OneToOne
    public Project project;

    @NotNull
    public Date dateOfBreak;

    public Break(Developer developer, Project project, Date dateOfBreak) {
        this.developer = developer;
        this.project = project;
        this.dateOfBreak = dateOfBreak;
    }

    public Developer getDeveloper() {
        return developer;
    }

    @Override
    public int compareTo(Break o) {
        return this.dateOfBreak.compareTo(o.dateOfBreak);
    }
}
