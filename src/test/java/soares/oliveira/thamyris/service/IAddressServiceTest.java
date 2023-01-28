package soares.oliveira.thamyris.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import soares.oliveira.thamyris.domain.Address;
import soares.oliveira.thamyris.domain.Person;
import soares.oliveira.thamyris.dto.address.AddressDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static soares.oliveira.thamyris.utils.Constraints.BRAZIL_FORMATTER;

@SpringBootTest
class IAddressServiceTest {

    @Autowired IPersonService personService;

    @Autowired IAddressService addressService;

    Person createPerson(String name, String birthdate){

        Person person = new Person();
        person.setName(name);
        person.setBirthdate( LocalDate.parse(birthdate, BRAZIL_FORMATTER) );

        return person;
    }

    private Address createAddres() {
        Address address = new Address();

        address.setNumber(1);
        address.setDistrict("1");
        address.setCity("1");
        address.setZipCode("1");

        return address;
    }

    @BeforeEach
    void setUp() {
        personService.insert( createPerson("Maria", "01/01/2001") );
        personService.insert( createPerson("Joao", "01/01/1991") );

        addressService.createAddress(createAddres(), 1);
    }


    @Test
    void createAddress() {

        Address address = new Address();
        address.setZipCode("123");
        address.setCity("123");
        address.setDistrict("123");
        address.setNumber(123);

        addressService.createAddress(address, 1);

        AddressDto newAddress = addressService.findAddresByPerson(1L).stream().filter(x -> x.city().equals("123") ).findFirst().get();

        Assertions.assertEquals(newAddress.zipCode(), address.getZipCode());
        Assertions.assertEquals(newAddress.city(), address.getCity());
        Assertions.assertEquals(newAddress.district(), address.getDistrict());
        Assertions.assertEquals(newAddress.number(), address.getNumber());

    }

    @Test
    void findAddresByPerson() {
        Address address = new Address();
        address.setZipCode("123");
        address.setCity("123");
        address.setDistrict("123");
        address.setNumber(123);

        addressService.createAddress(address, 1);

        List<AddressDto> addreses = addressService.findAddresByPerson(1L);
        Optional<AddressDto> optaddress = addreses.stream().filter(x -> x.zipCode().equals("123")).findFirst();

        Assertions.assertEquals(true, optaddress.isPresent());



    }
}