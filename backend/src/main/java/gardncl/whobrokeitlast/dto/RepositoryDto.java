package gardncl.whobrokeitlast.dto;

import com.google.gson.Gson;
import org.eclipse.egit.github.core.Repository;

public class RepositoryDto {

    public String url;
    public String projectTitle;

    public RepositoryDto(Repository repository) {
        this.url = repository.getUrl();
        this.projectTitle = repository.getName();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
