package soares.oliveira.thamyris.repositories;

import org.springframework.stereotype.Repository;
import soares.oliveira.thamyris.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}