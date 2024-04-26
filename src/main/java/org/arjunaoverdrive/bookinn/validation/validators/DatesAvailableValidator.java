package org.arjunaoverdrive.bookinn.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.domain.entities.Room;
import org.arjunaoverdrive.bookinn.service.RoomService;
import org.arjunaoverdrive.bookinn.validation.annotations.DatesAvailable;
import org.arjunaoverdrive.bookinn.web.payload.booking.UpsertBookingRequest;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DatesAvailableValidator implements ConstraintValidator<DatesAvailable, UpsertBookingRequest> {

    private final RoomService roomService;

    @Override
    public boolean isValid(UpsertBookingRequest request, ConstraintValidatorContext context) {
        Room room = roomService.findById(request.getRoomId());

        LocalDate checkinDate = request.getCheckinDate();
        LocalDate checkoutDate = request.getCheckoutDate();

        Set<LocalDate> bookedDates = room.getBookedDates().stream()
                .filter(ld -> !ld.isBefore(checkinDate) && !ld.isAfter(checkoutDate))
                .collect(Collectors.toSet());

        return bookedDates.isEmpty();
    }
}
