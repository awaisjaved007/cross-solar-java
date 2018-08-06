package com.crossover.techtrial.service;

import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import com.crossover.techtrial.repository.HourlyElectricityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringRunner.class)
public class HourlyElectricityServiceImplTest {
    @TestConfiguration
    static class HourlyElectricityServiceImplTestContextConfiguration {

        @Bean
        public HourlyElectricityService hourlyElectricityService() {
            return new HourlyElectricityServiceImpl();
        }
    }

    @Autowired
    private HourlyElectricityService hourlyElectricityService;

    @MockBean
    private HourlyElectricityRepository hourlyElectricityRepository;

    @MockBean
    Pageable pageable;


    Page pageFound;

    @Before
    public void setUp() {
        Long panelId = 233333L;
        Panel panel = new Panel();
        panel.setId(panelId);
        panel.setSerial("panel_Id12345678");

        HourlyElectricity hourlyElectricity_1 = new HourlyElectricity();
        hourlyElectricity_1.setId(32233322L);
        hourlyElectricity_1.setPanel(panel);

        HourlyElectricity hourlyElectricity_2 = new HourlyElectricity();
        hourlyElectricity_2.setId(32233324L);
        hourlyElectricity_2.setPanel(panel);

        HourlyElectricity hourlyElectricity_3 = new HourlyElectricity();
        hourlyElectricity_3.setId(32233324L);
        hourlyElectricity_3.setPanel(panel);

        ArrayList<HourlyElectricity> list = new ArrayList<>();
        list.add(hourlyElectricity_1);
        list.add(hourlyElectricity_2);
        list.add(hourlyElectricity_3);
        pageFound = new PageImpl<HourlyElectricity>(list);

        Mockito.when(hourlyElectricityRepository.findAllByPanelIdOrderByReadingAtDesc(panelId, pageable))
                .thenReturn(pageFound);
    }

    @Test
    public void whenFindByPanelId_thenReturnHourlyElectricityPageOrderByReading(){
        Page page = hourlyElectricityService.getAllHourlyElectricityByPanelId(233333L,pageable);
        assertEquals(page,pageFound);
    }

    @Test
    public void whenFindByIncorrectPanelId_thenReturnHourlyElectricityPageOrderByReadingNotFound(){
        Page page = hourlyElectricityService.getAllHourlyElectricityByPanelId(233334L,pageable);
        assertNull(page);
    }

}
