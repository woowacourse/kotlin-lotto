package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `구입 개수만큼 로또를 발행한다`() {
        assertThat(LottoGenerator.generate(14).size).isEqualTo(14)
    }

    @Test
    fun `랜덤한 숫자로 로또를 발행한다`() {
        assertThat(
            TestNumberGenerator(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }).generate()
        ).isEqualTo(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })
    }

    class TestNumberGenerator(private val numbers: List<LottoNumber>) : LottoNumberGenerator {
        override fun generate(): List<LottoNumber> {
            return numbers
        }
    }
}
