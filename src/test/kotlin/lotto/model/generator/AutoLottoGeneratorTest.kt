package lotto.model.generator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AutoLottoGeneratorTest {

    @Test
    fun `자동 로또를 만들어낸다`() {
        assertThat(
            AutoLottoGenerator().generate().lotto.size
        ).isEqualTo(6)
    }
}
