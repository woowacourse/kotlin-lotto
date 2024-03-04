package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PriceTest {
    @Test
    fun name() {
        assertThrows<IllegalArgumentException> {
            Price(-1)
        }
    }
}
