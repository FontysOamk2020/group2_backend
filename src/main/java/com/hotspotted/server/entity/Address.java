package com.hotspotted.server.entity;

import com.hotspotted.server.entity.enums.Planet;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Address extends BaseEntity implements Serializable {


    @OneToOne(
            fetch = FetchType.EAGER
    )
    private HotSpot hotSpot;

    @NotBlank
    private String address;
    private String postalCode;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @NotNull
    private Planet planet = Planet.EARTH;
}