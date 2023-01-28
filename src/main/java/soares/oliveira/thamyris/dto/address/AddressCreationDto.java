package soares.oliveira.thamyris.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import soares.oliveira.thamyris.domain.Address;

/**
 * A DTO for the {@link soares.oliveira.thamyris.domain.Address} entity
 */
public record AddressCreationDto(

        @JsonProperty(value = "id_pessoa", required = true) Long idUser,
        @JsonProperty(value = "logradouro") String district,
        @JsonProperty(value = "cep") String zipCode,
        @JsonProperty(value = "número") int number,
        @JsonProperty(value = "cidade") String city
){

    public static Address convertAddressCreationDtoToAddress(AddressCreationDto addressCreationDto){
        Address result = new Address();

        result.setCity(addressCreationDto.city());
        result.setDistrict(addressCreationDto.district());
        result.setNumber(addressCreationDto.number());
        result.setZipCode(addressCreationDto.zipCode());

        return result;
    }
}