package com.bol.mancalakalahagame.model.domain;

import com.bol.mancalakalahagame.model.enums.PitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PitTest {

    private Pit smallPit;
    private Pit largePit;

    @BeforeEach
    void setUp() {
        smallPit = new Pit(PitType.SMALL, 6);
        largePit = new Pit(PitType.LARGE, 0);
    }

    @Test
    void getNumberOfStones() {

        assertEquals(6, smallPit.getNumberOfStones());
        assertEquals(0, largePit.getNumberOfStones());

    }

    @Test
    void getPitType() {

        assertEquals(PitType.SMALL, smallPit.getPitType());
        assertEquals(PitType.LARGE, largePit.getPitType());
    }

    @Test
    void increaseNumberOfStones() {

        smallPit.increaseNumberOfStones(6);
        assertEquals(12, smallPit.getNumberOfStones());

        largePit.increaseNumberOfStones(6);
        assertEquals(6, largePit.getNumberOfStones());
    }

    @Test
    void pickAllStones() {

        smallPit.pickAllStones();
        assertEquals(0, smallPit.getNumberOfStones());

        largePit.pickAllStones();
        assertEquals(0, largePit.getNumberOfStones());

    }

    @Test
    void getOppositeIndex() {

        int oppositePitIndex = smallPit.getOppositeIndex(0);
        assertEquals(12, oppositePitIndex);
    }
}