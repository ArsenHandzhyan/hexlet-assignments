package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AppTest {


    @Test
    void testTake() {
        // BEGIN
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        int firsElement = numbers1.get(0);
        assertThat(firsElement).isEqualTo(1);
        assertThat(App.take(numbers1, 1)).hasSize(1);
        testRight();
        testWrong1();
        testWrong2();
        testWrong3();
    }

    void testRight() {
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        assertThat(Implementations.right(numbers1, 0)).hasSize(0);
    }

    void testWrong1() {
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        assertThat(Implementations.right(numbers1, 0)).hasSize(0);
    }

    void testWrong2() {
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        assertThat(Implementations.right(numbers1, 5)).hasSize(4);
    }

    void testWrong3() {
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        assertThat(Implementations.right(numbers1, 3)).hasSize(3);
    }
}
