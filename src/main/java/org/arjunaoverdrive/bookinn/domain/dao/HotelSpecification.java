package org.arjunaoverdrive.bookinn.domain.dao;

import org.arjunaoverdrive.bookinn.domain.entities.Hotel;
import org.arjunaoverdrive.bookinn.web.payload.hotel.HotelFilterRequest;
import org.springframework.data.jpa.domain.Specification;

public interface HotelSpecification {

    static Specification<Hotel> withFilter(HotelFilterRequest hotelFilterRequest){
        return Specification.where(byId(hotelFilterRequest.getId()))
                .and(byName(hotelFilterRequest.getName()))
                .and(byTitle(hotelFilterRequest.getTitle()))
                .and(byAddress(hotelFilterRequest.getAddress()))
                .and(byCity(hotelFilterRequest.getCity()))
                .and(byDistance(hotelFilterRequest.getDowntownDistance()))
                .and(byRating(hotelFilterRequest.getRating()))
                .and(byRates(hotelFilterRequest.getRatesCount()));
    }

    static Specification<Hotel> byId(Long hotelId){
        return (root, query, builder) -> {
            if(hotelId == null) return null;

            return builder.equal(root.get("id"), hotelId);
        };
    }

    static Specification<Hotel> byName(String name){
        return (root, query, builder) -> {
            if(name == null) return null;

            return builder.like(root.get("name"), "%" + name+ "%");
        };
    }

    static Specification<Hotel> byTitle(String title){
        return (root, query, builder) -> {
            if(title == null) return null;

            return builder.like(root.get("title"), "%" + title + "%");
        };
    }

    static Specification<Hotel> byAddress(String address){
        return (root, query, builder) -> {
            if(address == null) return null;

            return builder.like(root.get("address"), "%" + address + "%");
        };
    }

    static Specification<Hotel> byCity(String city){
        return (root, query, builder) -> {
            if(city == null) return null;

            return builder.equal(root.get("city"), city);
        };
    }

    static Specification<Hotel> byDistance(Double distance){
        return (root, query, builder) -> {
            if(distance == null) return null;

            return builder.lessThanOrEqualTo(root.get("distance"), distance);
        };
    }

    static Specification<Hotel> byRating(Double rating){
        return (root, query, builder) -> {
            if(rating == null) return null;

            return builder.equal(root.get("rating"), rating);
        };
    }

    static Specification<Hotel> byRates(Integer rates){
        return (root, query, builder) -> {
            if(rates == null) return null;

            return builder.equal(root.get("ratesCount"), rates);
        };
    }
}
