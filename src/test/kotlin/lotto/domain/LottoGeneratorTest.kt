package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `구입 개수만큼 로또를 발행한다`() {
        assertThat(LottoGenerator.generate(14).size).isEqualTo(14)
    }
}
