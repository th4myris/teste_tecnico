package soares.oliveira.thamyris.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import soares.oliveira.thamyris.domain.Person;

import java.time.LocalDate;

import static soares.oliveira.thamyris.utils.Constraints.BRAZIL_FORMATTER;

/**
 * A DTO for the {@link Person} entity
 */
public record PersonCreationDto(
        @JsonProperty(value = "nome", required = true) String name,
        @JsonProperty(value = "data_nascimento", required = true) String birthdate) {

    public static Person converterPersonCreationDtoToPerson(PersonCreationDto personCreationDto) {
        Person person = new Person();

        LocalDate data = LocalDate.parse(personCreationDto.birthdate, BRAZIL_FORMATTER);

        person.setName(personCreationDto.name());
        person.setBirthdate(data);
        return person;
    }
}