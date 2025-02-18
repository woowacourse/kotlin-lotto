package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또 번호는 1~45 사이이다`() {
        val lotto = Lotto()
        assertThat(lotto.number in 1..45)
    }
}
