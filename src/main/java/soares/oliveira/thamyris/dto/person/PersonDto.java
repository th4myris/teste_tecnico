package soares.oliveira.thamyris.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import soares.oliveira.thamyris.domain.Person;

import java.time.LocalDate;

import static soares.oliveira.thamyris.utils.Constraints.BRAZIL_FORMATTER;

/**
 * A DTO for the {@link Person} entity
 */
public record PersonDto(
        @JsonProperty(value = "id", required = true) Long id,
        @JsonProperty(value = "nome", required = true) String name,
        @JsonProperty(value = "data_nascimento", required = true) String birthdate) {

    public static PersonDto converterPersonToPersonDto(Person person) {
        String data = BRAZIL_FORMATTER.format(person.getBirthdate());
        PersonDto personDto = new PersonDto(person.getId(), person.getName(), data);
        return personDto;
    }

    public static Person converterPersonDtoToPerson(PersonDto personDto) {

        LocalDate data = LocalDate.parse(personDto.birthdate, BRAZIL_FORMATTER);

        Person person = new Person(personDto.id(), personDto.name(), data);
        return person;
    }
}