package soares.oliveira.thamyris.service;

import org.springframework.stereotype.Service;
import soares.oliveira.thamyris.domain.Person;

import java.util.List;

@Service
public interface IPersonService extends IBaseService<Person> {
    List<Person> findByName(String name);

    public Person findPersonById(Long id);

    void updateMainAddress(long idPerson, long idAddress);
}
