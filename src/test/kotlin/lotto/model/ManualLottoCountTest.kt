package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ManualLottoCountTest {
    @Test
    fun `0이상의 정수이어야 한다`() {
        assertThrows<IllegalArgumentException> {
            ManualLottoCount(-1)
        }
    }
}
