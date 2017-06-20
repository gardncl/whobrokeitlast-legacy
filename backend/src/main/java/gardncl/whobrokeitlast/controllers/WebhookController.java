package gardncl.whobrokeitlast.controllers;

import com.google.appengine.repackaged.com.google.gson.JsonElement;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import gardncl.whobrokeitlast.dao.DeveloperDao;
import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.services.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class WebhookController {

    @Autowired
    private ParseService parseService;

    @Autowired
    private DeveloperDao developerDao;

    @RequestMapping(
            value = "/travis-ci",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String consume(@RequestParam MultiValueMap<String, String> body) {
        JsonObject json = parseService.percentEncodingToJson(body.getFirst("payload")).getAsJsonObject();
        JsonElement repository = json.get("repository");
        JsonElement matrix = json.get("matrix").getAsJsonArray().get(0);
        JsonElement state = json.get("state");
        String userName = getEntry(matrix, "author_name");
        Developer developer = developerDao.getByUserName(userName);
        if (developer == null)
            developer = new Developer(userName,getEntry(matrix, "author_email"));
        developer.setLastBreak(new Date());
        developerDao.save(developer);
        String response = (getEntry(repository, "name") + " was broken by ") + userName;
        return state.toString().contains("errored") ? response: "Build passed";
    }

    private String getEntry(JsonElement element, String key) {
        return trim(element.getAsJsonObject().get(key).toString());
    }

    private String trim(String input) {
        return input.substring(1,input.length()-1);
    }

    private List<String> list = Arrays.asList("repository", "status_message", "committer_name");
}
