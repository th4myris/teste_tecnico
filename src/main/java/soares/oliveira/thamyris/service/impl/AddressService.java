package soares.oliveira.thamyris.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soares.oliveira.thamyris.domain.Address;
import soares.oliveira.thamyris.domain.Person;
import soares.oliveira.thamyris.dto.address.AddressDto;
import soares.oliveira.thamyris.repositories.AddressRepository;
import soares.oliveira.thamyris.service.IAddressService;
import soares.oliveira.thamyris.service.IPersonService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService extends BaseService<Address> implements IAddressService{

    private final AddressRepository addressRepository;
    private final IPersonService personService;

    @Autowired
    public AddressService( AddressRepository addressRepository, IPersonService personService ) {
        super(addressRepository);
        this.addressRepository = addressRepository;
        this.personService = personService;
    }

    public void createAddress(Address address, long idPerson){

        Person person = personService.findPersonById(idPerson);
        Address newAddress = addressRepository.save(address);

        // Adicionar o primeiro endereço como o principal
        if(person.getAddress().isEmpty()){
            person.setMainAdsress(newAddress);
        }

        person.getAddress().add(newAddress);

        personService.update(person);
    }

    public List<AddressDto> findAddresByPerson(Long idPerson){

        Person person = personService.findPersonById(idPerson);

        List<Address> listAddress = person.getAddress();

        List<AddressDto> result = new ArrayList<>();

        for ( Address address : listAddress) {
            // verificar se o endereço é o principal.
            boolean isMainAddress = address.getId().equals(person.getMainAddress().getId());
            AddressDto addressDto = AddressDto.converterAddresToAddressDto( address, isMainAddress );
            result.add(addressDto);
        }

        return result;

    }
}
