package soares.oliveira.thamyris.service;

import org.springframework.stereotype.Service;
import soares.oliveira.thamyris.domain.Address;
import soares.oliveira.thamyris.dto.address.AddressDto;

import java.util.List;

@Service
public interface IAddressService extends IBaseService<Address> {

    void createAddress(Address Address, long idPerson);

    List<AddressDto> findAddresByPerson(Long idPerson);
}