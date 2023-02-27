package lotto.model.generator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoNumberGeneratorTest {

    @Test
    fun `자동 로또를 만들어낸다`() {
        assertThat(
            LottoNumberGenerator().generate().lotto.size
        ).isEqualTo(6)
    }
}
