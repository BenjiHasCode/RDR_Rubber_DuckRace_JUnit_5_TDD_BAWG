import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void add() {
        Queue queue = new Queue();
        queue.add(new Duck(1));

        assertNotNull(queue.get(0));
        assertEquals(1, queue.get(0).getNumber());
    }

    @Test
    void remove() {
        Queue queue = new Queue();
        queue.add(new Duck(1));
        queue.add(new Duck(2));
        queue.add(new Duck(3));

        queue.remove();
        assertNotEquals(1, queue.get(0).getNumber());
        Duck duck = queue.remove();
        assertNotNull(duck);
        assertEquals(2, duck.getNumber());
    }

    @Test
    void size() {
        Queue queue = new Queue();
        queue.add(new Duck(1));
        queue.add(new Duck(11));
        queue.add(new Duck(111));

        assertEquals(3, queue.size());
    }
}