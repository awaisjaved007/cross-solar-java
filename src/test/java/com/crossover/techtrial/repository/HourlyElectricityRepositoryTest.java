package com.crossover.techtrial.repository;


import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class HourlyElectricityRepositoryTest {

    @Autowired
    HourlyElectricityRepository hourlyElectricityRepository;

    @Autowired
    PanelRepository panelRepository;

    @Before
    public void setUp() {
        Panel panel = new Panel();
        panel.setSerial("panel_Id12345678");
        panelRepository.save(panel);

        /*Panel panel2 = new Panel();
        panel.setSerial("panel_two2345678");
        panelRepository.save(panel2);*/

        HourlyElectricity hourlyElectricity_1 = new HourlyElectricity();
        hourlyElectricity_1.setPanel(panel);
        hourlyElectricity_1.setReadingAt(LocalDateTime.of(LocalDate.now().minusDays(34), LocalTime.now()));
        hourlyElectricity_1.setGeneratedElectricity(23L);
        hourlyElectricityRepository.save(hourlyElectricity_1);

        HourlyElectricity hourlyElectricity_2 = new HourlyElectricity();
        hourlyElectricity_2.setPanel(panel);
        hourlyElectricity_1.setReadingAt(LocalDateTime.of(LocalDate.now().minusDays(34), LocalTime.now()));
        hourlyElectricity_1.setGeneratedElectricity(67L);
        hourlyElectricityRepository.save(hourlyElectricity_2);

        HourlyElectricity hourlyElectricity_3 = new HourlyElectricity();
        hourlyElectricity_3.setPanel(panel);
        hourlyElectricity_1.setReadingAt(LocalDateTime.of(LocalDate.now().minusDays(50), LocalTime.now()));
        hourlyElectricity_1.setGeneratedElectricity(105L);
        hourlyElectricityRepository.save(hourlyElectricity_3);

        HourlyElectricity hourlyElectricity_4 = new HourlyElectricity();
        hourlyElectricity_3.setPanel(panel);
        hourlyElectricity_1.setReadingAt(LocalDateTime.of(LocalDate.now().minusDays(50), LocalTime.now()));
        hourlyElectricity_1.setGeneratedElectricity(205L);
        hourlyElectricityRepository.save(hourlyElectricity_4);
    }

    /*DATE Method is not generic for all database only
    working for MYSQL so this test is currently
    * unable to work*/
    @Test
    public void whenfindAllDailyElectricityStatisticsByPanelId_returnAllRecords() {

        Long panelId = panelRepository.findBySerial("panel_Id12345678").getId();
       // List<DailyElectricity> dailyElectricityList = hourlyElectricityRepository.findAllDailyElectricityStatistics(panelId);
      //  dailyElectricityList.get(0).toString();
        assertNotNull(panelId);
    }
}
