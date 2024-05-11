package org.arjunaoverdrive.bookinn.service.csv.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.bookinn.domain.dao.BookingMessageRepository;
import org.arjunaoverdrive.bookinn.domain.entities.mongo.BookingMongoEntity;
import org.arjunaoverdrive.bookinn.service.csv.StatisticsService;
import org.arjunaoverdrive.bookinn.service.dto.StatisticsDto;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {

    private final BookingMessageRepository bookingMessageRepository;

    @Override
    public List<StatisticsDto> getAll(LocalDate from, LocalDate to) {

        Map<BigInteger, List<BookingMongoEntity>> userToBooking =
                bookingMessageRepository.findByCheckinDateBetweenAndCheckoutDateBetween(from, to).stream()
                        .collect(Collectors.groupingBy(BookingMongoEntity::getUserId));

        List<StatisticsDto> result = new ArrayList<>();

        userToBooking.forEach((key, value) -> value.stream()
                .map(b -> StatisticsDto.builder()
                        .userId(key)
                        .bookingId(b.getBookingId())
                        .checkinDate(b.getCheckinDate())
                        .checkoutDate(b.getCheckoutDate())
                        .build())
                .sorted(Comparator.comparing(StatisticsDto::getUserId)
                        .thenComparing(StatisticsDto::getCheckinDate)
                        .thenComparing(StatisticsDto::getCheckoutDate))
                .forEach(result::add));

        return result;
    }
}
