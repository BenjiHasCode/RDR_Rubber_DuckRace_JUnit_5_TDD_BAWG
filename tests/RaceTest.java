import org.junit.jupiter.api.Test;

import java.lang.invoke.StringConcatFactory;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {

    @Test
    void iteration() {
        Race race = new Race();
        race.go();
        assertEquals(1, race.getQueues().size());

        Race race2 = new Race();
        for(int i = 0; i < 5; i++) {
            race2.setIteration(race2.getIteration() + 1);
            race2.iteration();// 5 iterations (we´ll be in iteration 6 (we start in one and iterate five times))
        }
        assertEquals(5, race2.getQueues().size());
        assertEquals(5, race2.getQueues().get(0).size());
    }

    @Test
    void cheatIteration() {
        Race race = new Race();
        race.go(20);
        assertEquals(1, race.getQueues().size());
        assertEquals(20, race.getQueues().get(0).get(0).getNumber());

        Race race2 = new Race();
        for(int i = 0; i < 5; i++) {
            race2.setIteration(race2.getIteration() + 1);
            race2.iteration(55);// 5 iterations (we´ll be in iteration 6 (we start in one and iterate five times))
        }
        assertEquals(5, race2.getQueues().size());
        assertEquals(5, race2.getQueues().get(0).size());
        assertEquals(55, race2.getQueues().get(0).get(0).getNumber());

    }
}