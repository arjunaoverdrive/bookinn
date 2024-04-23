package org.arjunaoverdrive.bookinn.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "hotel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_seq_generator")
    @SequenceGenerator(name = "hotel_seq_generator", allocationSize = 10)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(name = "downtown_distance")
    private Double downtownDistance;

    private BigDecimal rating;

    @Column(name = "rates_count")
    private Integer ratesCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel hotel)) return false;
        return Objects.equals(getName(), hotel.getName())
                && Objects.equals(getCity(), hotel.getCity())
                && Objects.equals(getAddress(), hotel.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCity(), getAddress());
    }
}
