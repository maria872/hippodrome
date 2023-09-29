import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    public void emptyHorsesException() {
       IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void nullHorsesException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));

            Hippodrome hippodrome = new Hippodrome(horses);
            assertEquals(horses, hippodrome.getHorses());
        }
    }

    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for(Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinner() {
        Horse horse1 = new Horse("Lily", 32, 204);
        Horse horse2 = new Horse("Dolly", 22, 510);
        Horse horse3 = new Horse("Honey", 19, 650);
        Horse horse4 = new Horse("Loren", 34, 265);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4));

        assertSame(horse3, hippodrome.getWinner());
    }
}