package lotto

import lotto.service.LottoAmountCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoAmountCalculatorTest {
    @Test
    fun `로또는 1000원 단위로 구입할 수 있다`() {
        val amountOfPurchase = 10001
        assertThrows<IllegalArgumentException> { LottoAmountCalculator(amountOfPurchase) }
    }

    @Test
    fun `구입 금액에 맞는 로또 개수를 계산할 수 있다`() {
        val amountOfPurchase = 10000
        assertThat(LottoAmountCalculator(amountOfPurchase).calculateAmountOfLottos()).isEqualTo(10)
    }

    @Test
    fun `수동 로또 개수에 따라 자동 로또의 개수를 계산할 수 있다`() {
        val calculator = LottoAmountCalculator(5000)

        assertThat(LottoAmountCalculator(2)).isEqualTo(3)
        assertThat(LottoAmountCalculator(0)).isEqualTo(5)
        assertThat(LottoAmountCalculator(5)).isEqualTo(0)
    }

    @Test
    fun `수동 로또 개수가 총 개수를 초과하면 예외가 발생한다`() {
        val calculator = LottoAmountCalculator(5000)

        assertThrows<IllegalArgumentException> {
            LottoAmountCalculator(6)
        }
    }
}
