package soares.oliveira.thamyris.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SearchDto(
        @JsonProperty(value = "pesquisa", required = false) String value
){
}
