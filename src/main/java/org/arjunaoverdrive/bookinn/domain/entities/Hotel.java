package org.arjunaoverdrive.bookinn.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "hotel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_seq_generator")
    @SequenceGenerator(name = "hotel_seq_generator", sequenceName = "hotel_seq", allocationSize = 10)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(name = "downtown_distance")
    private Double downtownDistance;

    private Double rating = 0.;

    @Column(name = "rates_count")
    private Integer ratesCount = 0;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Room> rooms = new HashSet<>();

    public void addRoom(Room room){
        this.rooms.add(room);
        room.setHotel(this);
    }

    public void addRooms(Collection<Room> newRooms){
        newRooms.forEach(this::addRoom);
    }

    public void removeRoom(Room room){
        this.rooms.remove(room);
        room.setHotel(null);
    }

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
