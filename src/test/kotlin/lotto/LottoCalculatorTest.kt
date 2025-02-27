package lotto

import lotto.domain.LottoCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoCalculatorTest {
    @Test
    fun `로또는 1000원 단위로 구입할 수 있다`() {
        val amountOfPurchase = 10001
        assertThrows<IllegalArgumentException> { LottoCalculator(amountOfPurchase) }
    }

    @Test
    fun `구입 금액에 맞는 로또 개수를 계산할 수 있다`() {
        val amountOfPurchase = 10000
        assertThat(LottoCalculator(amountOfPurchase).calculateAmountOfLottos()).isEqualTo(10)
    }

    @Test
    fun `수동 로또 개수에 따라 자동 로또의 개수를 계산할 수 있다`() {
        val calculator = LottoCalculator(5000)

        assertThat(calculator.calculateAutoLottos(2)).isEqualTo(3)
        assertThat(calculator.calculateAutoLottos(0)).isEqualTo(5)
        assertThat(calculator.calculateAutoLottos(5)).isEqualTo(0)
    }

    @Test
    fun `수동 로또 개수가 총 개수를 초과하면 예외가 발생한다`() {
        val calculator = LottoCalculator(5000)

        assertThrows<IllegalArgumentException> {
            calculator.calculateAutoLottos(6)
        }
    }
}
