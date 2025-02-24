package lotto.domain.service

import lotto.domain.model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosGeneratorTest {
    @Test
    fun `로또 번호는 정렬되어야 한다`() {
        val numbers = listOf(2, 1, 5, 4, 6, 3)
        val actual = listOf(1, 2, 3, 4, 5, 6)

        val numberGenerator = FixedLottoGenerator(numbers)
        val lottosGenerator = LottosGenerator(numberGenerator)
        val lotto = lottosGenerator.generate(1000).first()

        assertThat(lotto.numbers.map { it.number }).isEqualTo(actual)
    }

    inner class FixedLottoGenerator(private val numbers: List<Int>) : LottoGenerator {
        override fun generate(lottoSize: Int): Lotto {
            val lottoNumbers = numbers.take(6)
            return Lotto(lottoNumbers)
        }
    }
}
