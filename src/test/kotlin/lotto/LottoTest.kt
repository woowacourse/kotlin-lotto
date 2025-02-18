package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또는 6개의 숫자를 가진다`() {
        val lotto = Lotto()
        assertThat(lotto.lottoNums.size).isEqualTo(6)
    }
}
