package org.arjunaoverdrive.bookinn.service.csv;

import org.arjunaoverdrive.bookinn.service.dto.StatisticsDto;

import java.time.LocalDate;
import java.util.List;

public interface StatisticsService {

     List<StatisticsDto> getAll(LocalDate from, LocalDate to);
}
