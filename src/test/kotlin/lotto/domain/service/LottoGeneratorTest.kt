package lotto.domain.service

import lotto.domain.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoGeneratorTest {
    @CsvSource(
        "1000, 1",
        "10000, 10",
        "14000, 14",
    )
    @ParameterizedTest
    fun `구매 금액으로 구매할 로또 개수를 계산할 수 있다`(
        amount: Int,
        count: Int,
    ) {
        val lottoGenerator = LottoGenerator()
        val lottos = lottoGenerator.generate(amount)

        assertThat(lottos.size).isEqualTo(count)
    }

    @Test
    fun `로또 번호는 정렬되어야 한다`() {
        val numbers = listOf(2, 1, 5, 4, 6, 3)
        val actual = listOf(1, 2, 3, 4, 5, 6)

        val numberGenerator = FixedLottoNumberGenerator(numbers)
        val lottoGenerator = LottoGenerator(numberGenerator)
        val lotto = lottoGenerator.generate(1000).first()

        assertThat(lotto.numbers.map { it.number }).isEqualTo(actual)
    }

    inner class FixedLottoNumberGenerator(private val numbers: List<Int>) : LottoNumberGenerator {
        override fun generate(): List<LottoNumber> {
            return numbers.map { LottoNumber(it) }.sorted()
        }
    }
}
