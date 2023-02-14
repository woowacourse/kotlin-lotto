package lotto

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class LottoTest {
    @Test
    fun `로또는 번호 여섯 개를 갖는다`() {
        val lotto = Lotto()
        assertThat(lotto.numbers.size).isEqualTo(6)
    }
}