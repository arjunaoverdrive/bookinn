package org.arjunaoverdrive.bookinn.domain.mappers;

import org.arjunaoverdrive.bookinn.domain.entities.Hotel;
import org.arjunaoverdrive.bookinn.web.payload.hotel.HotelListResponse;
import org.arjunaoverdrive.bookinn.web.payload.hotel.HotelResponse;
import org.arjunaoverdrive.bookinn.web.payload.hotel.UpsertHotelRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {

    Hotel toHotel(UpsertHotelRequest request);

    default Hotel toHotel(Long id, UpsertHotelRequest request){
        Hotel hotel = toHotel(request);
        hotel.setId(id);
        return hotel;
    }

    HotelResponse toResponse(Hotel hotel);

    default HotelListResponse toListResponse(Page<Hotel> hotelsPage){
        HotelListResponse response = new HotelListResponse();
        response.setHotels(hotelsPage.getContent().stream()
                .map(this:: toResponse)
                .toList()
        );
        response.setTotalPages(hotelsPage.getTotalPages());
        return response;
    }
}
