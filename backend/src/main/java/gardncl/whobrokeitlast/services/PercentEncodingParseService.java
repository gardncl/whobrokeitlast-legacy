package gardncl.whobrokeitlast.services;

import com.google.appengine.repackaged.com.google.gson.JsonElement;
import com.google.appengine.repackaged.com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PercentEncodingParseService {

    private static Map<String, String> percentEncodingToJsonMap;

    public JsonElement percentEncodingToJson(String source) {
        for (Map.Entry<String, String> entry  : percentEncodingToJsonMap.entrySet()) {
            String find = entry.getKey();
            String replace = entry.getValue();
            if (source.contains(find)) {
                Pattern pattern = Pattern.compile(find);
                Matcher matcher = pattern.matcher(source);
                source = matcher.replaceAll(replace);
            }
        }
        return new JsonParser().parse(source);
    }

    static {
        Map<String, String> map = new HashMap<>();
        map.put("%21","!");
        map.put("%22","\"");
        map.put("%23","#");
        map.put("%24","$");
        map.put("%26","&");
        map.put("%27","\'");
        map.put("%28","(");
        map.put("%29",")");
        map.put("%2A","*");
        map.put("%2B","+");
        map.put("%2C",",");
        map.put("%2F","/");
        map.put("%3A",":");
        map.put("%3B",";");
        map.put("%3D","=");
        map.put("%3F","?");
        map.put("%40","@");
        map.put("%5B","[");
        map.put("%5D","]");
        map.put("%7B","{");
        map.put("%7D","}");
        percentEncodingToJsonMap = Collections.unmodifiableMap(map);
    }
}
