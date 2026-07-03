import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void nullListHorsesException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void isEmptyListHorseException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorses(){
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            horses.add(new Horse(i + "", i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();
        for(Horse horse: horses){
            verify(horse).move();
        }
    }

    @Test
    public void getWinner(){
        Horse horse1 = new Horse("name1", 1, 1);
        Horse horse2 = new Horse("name2", 2, 2);
        Horse horse3 = new Horse("name3", 3, 3);
        Horse horse4 = new Horse("name4", 4, 4);
        Horse horse5 = new Horse("name5", 5, 5);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2,horse3,horse4,horse5));

        assertEquals(horse5, hippodrome.getWinner());
    }
}
