package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottieCountTest {
    @Test
    fun `buying 금액으로 구매할 로또의 개수를 반환해준다`() {
        // given
        val lottoCount = 4
        val purchaseExpense = Money(3_000)
        val lottoPrice = Money(1_000)
        val expected = 3
        // when
        val actual = calculateLottoCount(lottoCount, lottoPrice, purchaseExpense)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `로또 count 보다 많은 로또 티켓 수는 발급할 수 없다`() {
        // given
        val lottoCount = 4
        val purchaseExpense = Money(5_000)
        val lottoPrice = Money(1_000)
        // when
        assertThrows<IllegalArgumentException> {
            calculateLottoCount(lottoCount, lottoPrice, purchaseExpense)
        }
    }

    fun calculateLottoCount(
        lottieCountLimit: Int,
        lottoPrice: Money,
        purchaseExpense: Money,
    ): Int {
        val count = purchaseExpense / lottoPrice
        if (lottieCountLimit < count) throw IllegalArgumentException()
        return count
    }
}
