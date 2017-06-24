package gardncl.whobrokeitlast.dto;

public class BreakDto {
    public String author;
    public String projectTitle;
    public String committerName;
    public String committerEmail;

    public BreakDto(String author, String projectTitle, String committerName, String committerEmail) {
        this.author = author;
        this.projectTitle = projectTitle;
        this.committerName = committerName;
        this.committerEmail = committerEmail;
    }
}
