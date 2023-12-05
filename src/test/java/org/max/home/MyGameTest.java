package org.max.home;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.max.seminar.Deck;

import java.util.ArrayList;
import java.util.List;

public class MyGameTest {
    static List<Door> doors;

    @BeforeEach
    void createDoors() {
        doors = new ArrayList<>();
        doors.add(new Door(true));
        doors.add(new Door(false));
        doors.add(new Door(false));
    }

    @Test
    void changeChoiceWin() {
        //Given
        Player player = new Player("Player", true);
        Game game = new Game(player, doors);
        //When
        Door door = game.round(1);
        //Then
        Assertions.assertTrue(door.isPrize());
    }

    @Test
    void changeChoiceLose() {
        //Given
        Player player = new Player("Player", true);
        Game game = new Game(player, doors);
        //When
        Door door = game.round(0);
        //Then
        Assertions.assertFalse(door.isPrize());
    }

    @Test
    void notChangeChoiceWin() {
        //Given
        Player player = new Player("Player", false);
        Game game = new Game(player, doors);
        //When
        Door door = game.round(0);
        //Then
        Assertions.assertTrue(door.isPrize());
    }

    @Test
    void notChangeChoiceLose() {
        //Given
        Player player = new Player("Player", false);
        Game game = new Game(player, doors);
        //When
        Door door = game.round(1);
        //Then
        Assertions.assertFalse(door.isPrize());
    }
}
