package soares.oliveira.thamyris.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soares.oliveira.thamyris.domain.Address;
import soares.oliveira.thamyris.domain.Person;
import soares.oliveira.thamyris.exception.address.notFoundAddressException;
import soares.oliveira.thamyris.exception.person.NotFoundPersonException;
import soares.oliveira.thamyris.repositories.PersonRepository;
import soares.oliveira.thamyris.service.IPersonService;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService extends BaseService<Person> implements IPersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        super(personRepository);
        this.personRepository = personRepository;
    }

    public List<Person> findByName(String name){
        return personRepository.findByNameLike( "%"+name+"%");
    }

    public void updateMainAddress(long idPerson, long idAddress){
        Person person = findPersonById(idPerson);

        Optional<Address> optAddress = person.getAddress().stream().filter(x -> x.getId() == idAddress ).findFirst();

        if(optAddress.isEmpty())
            throw new notFoundAddressException();

        Address address = optAddress.get();
        person.setMainAdsress(address);

        update(person);
    }

    @Override
    public Person findPersonById(Long id) {
        Optional<Person> optPerson = findById(id);

        // verificar se existe pessoa
        if(optPerson.isEmpty())
            throw new NotFoundPersonException();

        Person person = optPerson.get();

        return person;
    }
}
