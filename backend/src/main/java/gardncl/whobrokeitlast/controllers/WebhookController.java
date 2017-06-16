package gardncl.whobrokeitlast.controllers;

import com.google.appengine.repackaged.com.google.gson.JsonArray;
import com.google.appengine.repackaged.com.google.gson.JsonElement;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.appengine.repackaged.com.google.gson.JsonParser;
import gardncl.whobrokeitlast.services.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class WebhookController {

    @Autowired
    private ParseService parseService;

    private MultiValueMap<String, String> travisValue = new LinkedMultiValueMap<>();

    @RequestMapping(
            value = "/travis-ci",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String consume(HttpServletRequest request, @RequestParam MultiValueMap<String, String> body) {
        JsonObject json = (JsonObject)parseService.percentEncodingToJson(body.getFirst("payload"));
        Set<Map.Entry<String, JsonElement>> set =  json.entrySet();
        StringBuilder response = new StringBuilder();
        String status = set.stream().filter(x -> x.getKey().equals("repository")).findFirst().map(x -> x.getValue().getAsString());
        if (status.equals("Errored")) {
            response.append(set.stream()
                    .filter(x -> x.getKey()
                            .equals("repository"))
                    .findFirst().map(x -> x.getValue().getAsString()));
            response.append(" was broken by ");
            response.append(set.stream().filter(x -> x.getKey().equals("committer_name"))
                    .findFirst().map(x -> x.getValue().getAsString()));
        }
        return response.toString().isEmpty() ?  "Build passed" : response.toString();
    }

    @RequestMapping(value = "/travis-ci", method = RequestMethod.GET)
    public String produce() {
        return travisValue.toString();
    }

    private List<String> list = Arrays.asList("repository", "status_message", "committer_name");
}
