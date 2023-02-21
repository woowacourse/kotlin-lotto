package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `구입 금액만큼 로또를 발행한다`() {
        val actual = LottoGenerator.generate(14).size
        assertThat(actual).isEqualTo(14)
    }
}
