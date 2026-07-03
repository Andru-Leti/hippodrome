import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()-> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", })
    public void spaceNameException(String input){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()-> new Horse(input, 1, 1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void negativeSpeedException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("test-name", -1, 1 ));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void negativeDistanceException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("test-name", 1, -1 ));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName(){
        String name = "testName";
        Horse testHorse = new Horse(name, 1, 1);

        String curName = testHorse.getName();
        assertEquals(name, curName);
    }

    @Test
    public void getSpeed(){
        double speed = 1 ;
        Horse testHorse = new Horse("testName", speed, 1);

        double curSpeed = testHorse.getSpeed();
        assertEquals(speed, curSpeed);
    }

    @Test
    public void getDistance(){
        double distance = 10;
        Horse testHorse = new Horse("testName", 1, distance);

        assertEquals(distance, testHorse.getDistance());
    }

    @Test
    public void zeroDistanceByDefault(){
        Horse testHorse = new Horse("testName", 1);

        assertEquals(0, testHorse.getDistance());
    }

    @Test
    public void moveUserGetRandom(){
        try(MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)){
            new Horse("name", 1, 2).move();
            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.0})
    void move(double random){
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("qwerty", 31, 283);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();
            assertEquals(283 + 31 * random, horse.getDistance());
        }
    }
}
