package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class DNSResponse {

    @JsonProperty("Status")
    private int status;
    @JsonProperty("AD")
    private boolean ad;

    @JsonProperty("Answer")
    private List<Answer> answers;

    @Data
    public static class Answer {

        private String name;
        private int type;
        private String data;
    }

    @JsonIgnore
    public boolean isDNSSECValidated() {
        return ad;
    }

    public List<String> getTXTStrings() {
        // DNS type 16 is TXT
        return answers.stream().filter(a -> a.getType() == 16).map(a -> a.getData()).collect(Collectors.toList());
    }
}
