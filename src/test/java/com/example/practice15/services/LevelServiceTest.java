package com.example.practice15.services;


import com.example.practice15.models.Level;
import com.example.practice15.repositories.LevelRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LevelServiceTest {
    @MockBean
    private LevelRepository levelRepository;

    @Captor
    private ArgumentCaptor<Level> captor;
    private LevelService levelService;

    @Autowired
    public void setLevelService(LevelService levelService) {
        this.levelService = levelService;
    }
    @Test
    public void addLevel() {
        Level level = new Level();
        level.setLevelName("five");
        level.setComplexity("high");
        levelService.addLevel(level);

        verify(levelRepository).save(captor.capture());
        Level captured = captor.getValue();
        Assertions.assertEquals(level.getLevelName(), captured.getLevelName());
        Assertions.assertEquals(level.getComplexity(),captured.getComplexity());
    }
    @Test
    public void findAllLevels() {
        Level level = new Level();
        level.setLevelName("six");
        Level level1 = new Level();
        level1.setLevelName("seven");
        when(levelRepository.findAll()).thenReturn(List.of(level,level1));
        Assertions.assertEquals(2, levelService.findAllLevels().size());
    }

}
