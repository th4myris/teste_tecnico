package soares.oliveira.thamyris.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import soares.oliveira.thamyris.domain.Address;
import soares.oliveira.thamyris.domain.Person;
import soares.oliveira.thamyris.exception.person.NotFoundPersonException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static soares.oliveira.thamyris.utils.Constraints.BRAZIL_FORMATTER;

@SpringBootTest
class IPersonServiceTest {

    @Autowired
    IPersonService personService;

    @Autowired
    IAddressService addressService;

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
    void findByName() {
        Person pMaria = personService.findByName("Ma").stream().findFirst().get();
        Person pJoao = personService.findByName("Jo").stream().findFirst().get();
        Optional<Person> pPedro = personService.findByName("Pedr").stream().findFirst();

        Assertions.assertEquals( pMaria.getName().contains("Lucas"), true );
        Assertions.assertEquals( pJoao.getName().contains("Tham"), true );
        Assertions.assertEquals( pPedro.isEmpty(), true );
    }

    @Test
    void findPersonById() {

        Person pMaria = personService.findPersonById(1L);
        Person pJoao = personService.findPersonById(2L);

        Exception exception = assertThrows(NotFoundPersonException.class, () -> {
            Person pPedro = personService.findPersonById(10000L);
        });

        String expectedMessage = "Pessoa não encontrada";
        String actualMessage = exception.getMessage();


        Assertions.assertEquals( pMaria.getId(), 1 );
        Assertions.assertEquals( pJoao.getId(), 1 );
        Assertions.assertEquals( expectedMessage, actualMessage );
    }

    @Test
    void updateMainAddres() {

        Address address = new Address();
        address.setZipCode("123");
        address.setCity("123");
        address.setDistrict("123");
        address.setNumber(123);

        addressService.createAddress(address, 1);

        Long idNewAddress = 2L;
        Long PersonId = 1L;

        personService.updateMainAddress(PersonId,idNewAddress);

        Address mainAddress = personService.findPersonById(PersonId).getMainAddress();
        Assertions.assertEquals( mainAddress.getId(), idNewAddress );
    }
}