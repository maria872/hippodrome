import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    public void nullNameException() {

        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void nullNameMessage() {
        try {
            new Horse(null, 1, 1);
            fail("Expected an exception to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n\n"})
    public void blankNameException(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void negativeSpeedException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Lily", -1, 1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void negativeDistanceException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Lily", 1, -1));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void getName() {
        Horse horse = new Horse("Lily", 1, 1);

        assertEquals("Lily", horse.getName());
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("Lily", 30, 1);

        assertEquals(30, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("Lily", 1, 180);

        assertEquals(180, horse.getDistance());
    }

    @Test
    public void getDistanceZero() {
        Horse horse = new Horse("Lily", 1);

        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveGetRandom() {
        try(MockedStatic<Horse> mockedStatic =  Mockito.mockStatic(Horse.class)) {
            new Horse("Lily", 26, 180).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.5, 0.9, 0.2})
    public void move(double random) {
        try(MockedStatic<Horse> mockedStatic =  Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Lily", 26, 180);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse.move();
            assertEquals(180 + 26*random, horse.getDistance());
        }
    }
}