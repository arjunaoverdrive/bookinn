package org.arjunaoverdrive.bookinn.domain.dao;

import jakarta.persistence.criteria.*;
import org.arjunaoverdrive.bookinn.domain.entities.Booking;
import org.arjunaoverdrive.bookinn.domain.entities.Room;
import org.arjunaoverdrive.bookinn.web.payload.room.RoomFilterRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;


public interface RoomSpecification {

    static Specification<Room> withFilter(RoomFilterRequest filter) {
        return Specification.anyOf(Specification.where(byId(filter.getId()))
                .and(byName(filter.getName()))
                .and(byMaxPrice(filter.getMaxPrice()))
                .and(byMinPrice(filter.getMinPrice()))
                .and(byCapacity(filter.getCapacity()))
                .and(byDates(filter.getCheckinDate(), filter.getCheckoutDate()))
                .and(byHotelId(filter.getHotelId())));
    }

    static Specification<Room> byId(Long id) {
        return (root, query, builder) -> {
            if (id == null) return null;

            return builder.equal(root.get("id"), id);
        };
    }

    static Specification<Room> byName(String name) {
        return (root, query, builder) -> {
            if (name == null) return null;

            return builder.like(root.get("name"), "%" + name + "%");
        };
    }

    static Specification<Room> byMaxPrice(Double maxPrice) {
        return (root, query, builder) -> {
            if (maxPrice == null) return null;

            return builder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    static Specification<Room> byMinPrice(Double minPrice) {
        return (root, query, builder) -> {
            if (minPrice == null) return null;

            return builder.greaterThanOrEqualTo(root.get("price"), minPrice);
        };
    }

    static Specification<Room> byCapacity(Integer capacity) {
        return (root, query, builder) -> {
            if (capacity == null) return null;

            return builder.equal(root.get("capacity"), capacity);
        };
    }

    static Specification<Room> byDates(LocalDate checkinDate, LocalDate checkoutDate) {

        return (root, query, builder) -> {
            if (checkinDate == null || checkoutDate == null) return null;

            Subquery<Room> subquery = query.subquery(Room.class);
            Root<Room> bookingRoot = subquery.from(Room.class);

            Join<Booking, Room> bookings = bookingRoot.join("bookings", JoinType.LEFT);

            Predicate checkin = builder.between(bookings.get("checkinDate"), checkinDate, checkoutDate);
            Predicate checkout = builder.between(bookings.get("checkoutDate"), checkinDate, checkoutDate);
            Predicate dates = builder.or(checkin, checkout);

            subquery.select(bookings.get("room")).where(dates);

            CriteriaQuery<?> rooms = query.where(builder.not(builder.in(root).value(subquery)));

            return rooms.getRestriction();
        };
    }


    static Specification<Room> byHotelId(Long hotelId) {
        return (root, query, builder) -> {
            if (hotelId == null) return null;

            return builder.equal(root.get("hotel").get("id"), hotelId);
        };
    }

}
