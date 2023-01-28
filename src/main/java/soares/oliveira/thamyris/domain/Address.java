package soares.oliveira.thamyris.domain;

import jakarta.persistence.*;

@Entity
@Table
public class Address extends BaseEntity {

    @Column
    private String district;
    @Column
    private String ZipCode;
    @Column
    private int Number;
    @Column
    private String City;

    public Address() {
    }

    public Address(Long id, String district, String zipCode, int number, String city) {
        super(id);
        this.district = district;
        ZipCode = zipCode;
        Number = number;
        City = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

}
