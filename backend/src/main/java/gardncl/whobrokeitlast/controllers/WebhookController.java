package gardncl.whobrokeitlast.controllers;

import gardncl.whobrokeitlast.services.TravisParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


//TODO: MAKE THIS CONTROLLER NOT SEND A RESPONSE ONCE DEVELOPMENT IS COMPLETE

@RestController
public class WebhookController {

    @Autowired
    private TravisParseService travisParseService;

    @RequestMapping(
            value = "/travis-ci",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String consume(@RequestParam MultiValueMap<String, String> body) {
        return travisParseService.processRequest(body);
    }

}
