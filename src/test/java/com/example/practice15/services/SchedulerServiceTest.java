package com.example.practice15.services;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SchedulerServiceTest {
    private SchedulerService schedulerService;
    @MockBean
    private LevelService levelService;
    @MockBean
    private GameService gameService;

    @Autowired
    public void setSchedulerService(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Test
    public void checkFilesExist() throws IOException {
        schedulerService.doTask();

        String path = "src/main/resources/output";
        File dir = ResourceUtils.getFile(path);
        verify(levelService, times(1)).findAllLevels();
        verify(gameService, times(1)).findAllGames();
        Assertions.assertTrue(dir.exists());
        String[] files = {"game_data.txt", "level_data.txt"};

        Assertions.assertTrue(Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                .map(File::getName)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList(files)));
    }
}
