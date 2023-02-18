package model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoCountTest {

    @Test
    fun `로또의 갯수가 1이상이 아니면 에러를 발생한다`() {
        // given
        val count = 0

        // then
        assertThrows<IllegalArgumentException> {
            LottoCount(count)
        }
    }
}
