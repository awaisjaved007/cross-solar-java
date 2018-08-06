package com.crossover.techtrial.repository;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * HourlyElectricity Repository is for all operations for HourlyElectricity.
 * @author Crossover
 */
@RestResource(exported = false)
public interface HourlyElectricityRepository extends PagingAndSortingRepository<HourlyElectricity,Long> {
    Page<HourlyElectricity> findAllByPanelIdOrderByReadingAtDesc(Long panelId, Pageable pageable);

    @Query(value ="SELECT DATE(e.reading_at) as reading_date,sum(e.generated_electricity)," +
            "avg(e.generated_electricity), min(e.generated_electricity),max(e.generated_electricity) FROM " +
            " hourly_electricity e  where e.panel_id = ?1 GROUP BY (reading_date)",nativeQuery = true)
    List findAllDailyElectricityStatistics(Long penalId);
}
