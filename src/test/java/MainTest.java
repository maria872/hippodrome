import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Timeout(value = 22000, unit = TimeUnit.MILLISECONDS)
    @Disabled("Тест временно отключен")
    void testMethodExecutionTime() throws Exception {
        Main.main(null);
    }
}