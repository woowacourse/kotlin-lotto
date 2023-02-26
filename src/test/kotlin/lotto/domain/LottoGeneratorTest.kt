package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    private val lottoGenerator = LottoGenerator(RandomNumberGenerator())

    @Test
    fun `구입 개수 만큼 로또를 발행한다`() {
        val actual = lottoGenerator.generate(14).size
        assertThat(actual).isEqualTo(14)
    }

    @Test
    fun `숫자를 받아서 수동 로또를 발행한다`() {
        val numbers = listOf(7, 8, 9, 10, 11, 12)
        val actual = lottoGenerator.generateManual(numbers)
        val expected = makeLotto(numbers)
        assertThat(actual).isEqualTo(expected)
    }

    private fun makeLotto(numbers: List<Int>): Lotto {
        return Lotto(numbers.map { LottoNumber.from(it) }.toSet())
    }
}
