package soares.oliveira.thamyris.controller;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import soares.oliveira.thamyris.domain.Address;
import soares.oliveira.thamyris.domain.Person;
import soares.oliveira.thamyris.dto.SearchDto;
import soares.oliveira.thamyris.dto.address.AddressCreationDto;
import soares.oliveira.thamyris.dto.address.AddressDto;
import soares.oliveira.thamyris.dto.address.UpdateMainAddresDto;
import soares.oliveira.thamyris.dto.person.PersonCreationDto;
import soares.oliveira.thamyris.dto.person.PersonDto;
import soares.oliveira.thamyris.service.IAddressService;
import soares.oliveira.thamyris.service.IPersonService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("person")
@RestController
public class PersonController {

    private IPersonService personService;
    private IAddressService addressService;

    @Autowired
    public PersonController( IPersonService personService, IAddressService addressService ){
        this.personService = personService;
        this.addressService = addressService;
    }

    @GetMapping("")
    public ResponseEntity<List<PersonDto>> listPersons( @RequestBody SearchDto search ){
        List<Person> persons = null;

        // validacao para consultar pelo nome
        if( search != null && !StringUtils.isBlank(search.value()) )
            persons = personService.findByName(search.value());
        else
            persons = personService.findAll();

        // conversao para visualização de usuario
        List<PersonDto> result = persons.stream()
                .map(PersonDto::converterPersonToPersonDto )
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("")
    public ResponseEntity createPerson(@RequestBody PersonCreationDto personCreationDto){
        Person person = PersonCreationDto.converterPersonCreationDtoToPerson(personCreationDto);
        long id = personService.insert( person ).getId();

        return ResponseEntity.created(URI.create("/person/"+id)).build();
    }

    @PutMapping("")
    public ResponseEntity updatePerson(@RequestBody PersonDto personDto){
        Person person = PersonDto.converterPersonDtoToPerson(personDto);
        long id = personService.update( person ).getId();

        return ResponseEntity.created(URI.create("/person/"+id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPerson( @PathVariable(value="id") Long id ){
        Person person = personService.findPersonById(id);

        PersonDto result = PersonDto.converterPersonToPersonDto(person);

        return ResponseEntity.ok().body(result);

    }

    /*
    * ENDPOINTS DE ENDEREÇO
    * */

    @PostMapping("/address")
    public ResponseEntity createAddres(@RequestBody AddressCreationDto addressCreationDto){

        Address address = AddressCreationDto.convertAddressCreationDtoToAddress(addressCreationDto);

        addressService.createAddress(address, addressCreationDto.idUser());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<List<AddressDto>> getAddresFromPerson(@PathVariable(value="id") Long idPerson){
        List<AddressDto> result = addressService.findAddresByPerson(idPerson);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/address/main_address")
    public ResponseEntity updateMainAddress(@RequestBody UpdateMainAddresDto updateMainAddresDto){
        personService.updateMainAddress(updateMainAddresDto.idPerson(), updateMainAddresDto.idAddress());
        return ResponseEntity.ok().build();
    }


}
