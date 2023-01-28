package soares.oliveira.thamyris.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import soares.oliveira.thamyris.domain.Address;

/**
 * A DTO for the {@link Address} entity
 */
public record AddressDto(
        @JsonProperty(value = "id")  Long id,
        @JsonProperty(value = "logradouro")  String district,
        @JsonProperty(value = "cep")  String zipCode,
        @JsonProperty(value = "número")  int number,
        @JsonProperty(value = "cidade")  String city,
        @JsonProperty(value = "endereco_principal")  boolean isMainAddres
){

    public static AddressDto converterAddresToAddressDto(Address address, boolean isMainAddress){
        return new AddressDto(
                                address.getId(),
                                address.getDistrict(),
                                address.getZipCode(),
                                address.getNumber(),
                                address.getCity(),
                                isMainAddress
                            );
    }

}