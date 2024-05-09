package it.gruppogaspari.personalinfo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BASICINFO")
public class BasicInfoEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // this is required if info_id AUTO_INCREMENT PRIMARY KEY, without this will throw exception
    @Column(name = "info_id")
    private int infoId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String email;
    @Column
    private String contact;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private AddressEntity address;

}





