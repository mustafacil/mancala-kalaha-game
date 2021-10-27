package com.bol.mancalakalahagame.service;

import com.bol.mancalakalahagame.model.domain.Pit;
import com.bol.mancalakalahagame.model.enums.PitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PitServiceTest {

    private PitService pitService;

    @BeforeEach
    void setUp(){
        pitService = new PitService();
    }

    @Test
    void createPitArray() {

        Pit[] pitArray = pitService.createPitArray(2);
        assertEquals(14, pitArray.length);

        assertEquals(PitType.SMALL, pitArray[0].getPitType());
        assertEquals(PitType.LARGE, pitArray[6].getPitType());

    }
}