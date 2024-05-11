package org.arjunaoverdrive.bookinn.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "booking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq_gen")
    @SequenceGenerator(name = "booking_seq_gen", sequenceName = "booking_seq", allocationSize = 30)
    private Long id;

    @Column(name = "checkin_date", nullable = false)
    private LocalDate checkinDate;

    @Column(name = "checkout_date", nullable = false)
    private LocalDate checkoutDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking booking)) return false;
        return Objects.equals(getCheckinDate(), booking.getCheckinDate())
                && Objects.equals(getUser(), booking.getUser())
                && Objects.equals(getRoom(), booking.getRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCheckinDate(), getUser(), getRoom());
    }
}
