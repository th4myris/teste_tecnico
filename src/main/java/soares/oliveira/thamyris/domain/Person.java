package soares.oliveira.thamyris.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Person extends BaseEntity  {

    @Column
    private String name;
    @Column
    private LocalDate birthdate;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Address> address;

    @OneToOne
    private Address mainAddress;

    public Person() {
    }

    public Person(long id, String name, LocalDate birthdate, List<Address> address, Address mainAddress) {
        super(id);
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.mainAddress = mainAddress;
    }

    public Person(long id, String name, LocalDate birthdate) {
        super(id);
        this.name = name;
        this.birthdate = birthdate;
        this.address = new ArrayList<>();
        this.mainAddress = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public Address getMainAddress() {
        return mainAddress;
    }

    public void setMainAdsress(soares.oliveira.thamyris.domain.Address mainAddress) {
        this.mainAddress = mainAddress;
    }

}
