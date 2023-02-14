package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoNumberGeneratorTest {
    @Test
    fun `생성된 숫자는 1에서 45 사이어야 한다`() {
        assertThat(LottoNumberGenerator().generate()).isBetween(1, 45)
    }
}
