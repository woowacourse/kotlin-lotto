package lotto.domain.service

import lotto.domain.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LottosGeneratorTest {
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
        val lottosGenerator = LottosGenerator()
        val lottos = lottosGenerator.generate(amount)

        assertThat(lottos.size).isEqualTo(count)
    }

    @Test
    fun `로또 번호는 정렬되어야 한다`() {
        val numbers = listOf(2, 1, 5, 4, 6, 3)
        val actual = listOf(1, 2, 3, 4, 5, 6)

        val numberGenerator = FixedLottoNumbersGenerator(numbers)
        val lottosGenerator = LottosGenerator(numberGenerator)
        val lotto = lottosGenerator.generate(1000).first()

        assertThat(lotto.numbers.map { it.number }).isEqualTo(actual)
    }

    @ValueSource(ints = [999, 0, -1000, -999])
    @ParameterizedTest
    fun `구매 금액이 로또 금액 미만이라면 예외가 발생한다`(purchaseAmount: Int) {
        assertThrows<IllegalArgumentException> {
            LottosGenerator().generate(purchaseAmount)
        }
    }

    inner class FixedLottoNumbersGenerator(private val numbers: List<Int>) : LottoNumbersGenerator {
        override fun generate(): List<LottoNumber> {
            return numbers.sorted().map { LottoNumber(it) }
        }
    }
}
