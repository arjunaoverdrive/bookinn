package org.arjunaoverdrive.bookinn.service.csv.Impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.arjunaoverdrive.bookinn.service.csv.CSVService;
import org.arjunaoverdrive.bookinn.service.csv.StatisticsService;
import org.arjunaoverdrive.bookinn.service.dto.StatisticsDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CSVServiceImpl implements CSVService {

    private final StatisticsService statisticsService;

    @Override
    public void getStats(LocalDate from, LocalDate to, HttpServletResponse response) {

        List<StatisticsDto> stats = statisticsService.getAll(from, to);
        String[] HEADERS = {"user_id", "booking_id", "checkin_date", "checkout_date"};
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS).build();

        try {
            export(response.getWriter(), stats, format);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(
                    MessageFormat.format("Cannot export data due to {0}", e.getMessage())
            );
        }
    }

    private void export(Writer writer, List<StatisticsDto> stats, CSVFormat format) {

        try (final CSVPrinter printer = new CSVPrinter(writer, format)) {
            stats.forEach((dto) -> {
                try {
                    printer.printRecord(dto.getUserId(), dto.getBookingId(), dto.getCheckinDate(), dto.getCheckoutDate());
                } catch (IOException e) {
                    log.warn(e.getMessage());
                }
            });

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
