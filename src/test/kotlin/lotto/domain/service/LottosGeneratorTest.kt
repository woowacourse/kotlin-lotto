package lotto.domain.service

import lotto.domain.model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LottosGeneratorTest {
    @CsvSource(
        "1000, 1",
        "10000, 10",
        "14000, 14",
    )
    @ParameterizedTest(name = "{0}원으로, {1}개를 구매할 수 있다")
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

        val numberGenerator = FixedLottoGenerator(numbers)
        val lottosGenerator = LottosGenerator(numberGenerator)
        val lotto = lottosGenerator.generate(1000).first()

        assertThat(lotto.numbers.map { it.number }).isEqualTo(actual)
    }

    @ValueSource(ints = [999, 0, -1000, -999])
    @ParameterizedTest
    fun `구매 금액이 1000원 미만이라면 예외가 발생한다`(purchaseAmount: Int) {
        assertThatThrownBy { LottosGenerator().generate(purchaseAmount) }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${purchaseAmount}원으로 로또를 구매하지 못했습니다 로또는 한 장 이상 구매해야 합니다.")
    }

    inner class FixedLottoGenerator(private val numbers: List<Int>) : LottoGenerator {
        override fun generate(lottoSize: Int): Lotto {
            val lottoNumbers = numbers.take(6)
            return Lotto(lottoNumbers)
        }
    }
}
