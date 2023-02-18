package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CountTest {

    @Test
    fun `갯수는 1 이상이다`() {
        // given
        val count = 0

        // then
        assertThrows<IllegalArgumentException> {
            LottoCount(count)
        }
    }
}
