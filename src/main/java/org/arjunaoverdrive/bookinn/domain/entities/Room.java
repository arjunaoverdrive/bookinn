package org.arjunaoverdrive.bookinn.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_gen")
    @SequenceGenerator(name = "room_id_gen", allocationSize = 30)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String number;

    private BigDecimal price;

    @Column(nullable = false)
    private Integer capacity;

    @Column(name = "booked_dates")
    private Set<LocalDate> bookedDates = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return Objects.equals(getName(), room.getName()) &&
                Objects.equals(getNumber(), room.getNumber()) &&
                Objects.equals(getHotel(), room.getHotel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNumber(), getHotel());
    }
}
