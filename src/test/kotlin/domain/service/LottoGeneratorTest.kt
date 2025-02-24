package domain.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `자동 로또 구매 개수가 2개면 자동 로또를 2개 발행한다`() {
        assertThat(LottoGeneratorImpl().generate(2).size).isEqualTo(2)
    }
}
