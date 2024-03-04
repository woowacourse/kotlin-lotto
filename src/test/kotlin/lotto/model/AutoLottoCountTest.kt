package lotto.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AutoLottoCountTest {
    @Test
    fun `자동로또개수는 양수이어야 한다`() {
        assertThrows<IllegalArgumentException> {
            AutoLottoCount(-1)
        }
    }
}
