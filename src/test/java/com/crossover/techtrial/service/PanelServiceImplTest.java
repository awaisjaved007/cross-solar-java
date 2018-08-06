package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Panel;
import com.crossover.techtrial.repository.PanelRepository;
import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringRunner.class)
public class PanelServiceImplTest {
    @TestConfiguration
    static class PanelServiceImplTestContextConfiguration {
        @Bean
        public PanelService panelService() {
            return new PanelServiceImpl();
        }
    }

    @Autowired
    private PanelService panelService;

    @MockBean
    private PanelRepository panelRepository;

    Panel panel;

    @Before
    public void setUp() {
        Long panelId = 233333L;
        panel = new Panel();
        panel.setId(panelId);
        panel.setSerial("panel_Id12345678");

        Mockito.when(panelRepository.findBySerial("panel_Id12345678")).thenReturn(panel);
    }

    @Test
    public void whenFindBySerialKey_ReturnPanelObject(){
        Panel panelObject = panelService.findBySerial("panel_Id12345678");
        assertEquals(panelObject,panel);
    }

    @Test
    public void whenFindByIncorrectSerialKey_ReturnNull(){
        Panel panelObject = panelService.findBySerial("panel_Id12345679");
        assertNull(panelObject);
    }
}
