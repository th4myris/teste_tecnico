package soares.oliveira.thamyris.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateMainAddresDto (
        @JsonProperty(value = "id_pessoa", required = true) long idPerson,
        @JsonProperty(value = "id_endereco", required = true) long idAddress
        ){
}
