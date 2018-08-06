package com.crossover.techtrial.repository;

import com.crossover.techtrial.model.Panel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PanelRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    PanelRepository panelRepository;

    Panel panel;

    @Before
    public void setUp() {
        Long panelId = 233333L;
        panel = new Panel();
        //panel.setId(panelId);
        panel.setSerial("panel_Id12345678");
        panelRepository.save(panel);


       // panelRepository.findBySerial("panel_Id12345678");
    }

    @Test
    public void whenRegisterNewPanelObject_ReturnPanelObject(){
        Panel panelObject = new Panel();
        panelObject.setSerial("panel_Id12948394");
        Panel savedObject = panelRepository.save(panel);
        assertNotNull(savedObject);
    }

    @Test(expected = ConstraintViolationException.class)
    public void whenRegisterNewPanelObjectWithInValidData_ReturnPanelObject(){
        Panel panelObject = new Panel();
        panelObject.setSerial("panel_Id129494");
        Panel savedObject = panelRepository.save(panelObject);
        assertNotNull(null);
    }

    @Test
    public void whenFindBySerialKey_ReturnPanelObject(){
        Panel panelObject = panelRepository.findBySerial("panel_Id12345678");
        assertEquals(panelObject,panel);
    }

    @Test
    public void whenFindByInCorrectSerialKey_ReturnPanelObject(){
        Panel panelObject = panelRepository.findBySerial("panel_Id12345679");
        assertEquals(panelObject,null);
    }

}
