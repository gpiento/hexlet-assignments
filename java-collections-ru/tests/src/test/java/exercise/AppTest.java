package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
        List<Integer> numbers1;
        List<Integer> numbers2;

    @BeforeEach
    void initList() {
        numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        numbers2 = new ArrayList<>(Arrays.asList(7, 3, 10));
    }

    @Test
    void take1Test() {
        assertThat(App.take(numbers1, 2))
                .isNotEmpty()
                .contains(1, 2);
    }

    @Test
    void take2Test() {
        assertThat(App.take(numbers2, 8))
                .isNotEmpty()
                .contains(7, 3, 10);
    }
}
