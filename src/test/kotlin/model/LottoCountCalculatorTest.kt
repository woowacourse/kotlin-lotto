package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoCountCalculatorTest {
    @Test
    fun `buying 금액으로 구매할 로또의 개수를 반환해준다`() {
        // given
        val lottoCount = LottoCount(4)
        val cost = Money(3_000)
        val expected = LottoCount(3)
        // when
        val calculator = LottoCountCalculator(lottoPrice = Money(1_000))
        val actual = calculator.calculate(limit = lottoCount, cost = cost)
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `로또 count 보다 많은 로또 티켓 수는 발급할 수 없다`() {
        // given
        val lottoCount = LottoCount(4)
        val cost = Money(5_000)
        val lottoPrice = Money(1_000)
        // when
        assertThrows<IllegalStateException> {
            LottoCountCalculator(lottoPrice).calculate(limit = lottoCount, cost = cost)
        }
    }

    class LottoCountCalculator(private val lottoPrice: Money) {
        fun calculate(
            limit: LottoCount = LottoCount.MAX,
            cost: Money,
        ): LottoCount {
            val count = LottoCount(cost / lottoPrice)
            check(limit > count) { "count=$count 는 limit=$limit 보다 작아야합니다." }
            return count
        }
    }

    @JvmInline
    value class LottoCount(val amount: Int) {
        init {
            require(amount > 0) { "amount 는 0보다 커야함" }
        }

        operator fun compareTo(o: LottoCount) = amount - o.amount

        operator fun plus(o: LottoCount) = LottoCount(amount + o.amount)

        operator fun minus(o: LottoCount) = LottoCount(amount - o.amount)

        companion object {
            val MAX = LottoCount(Int.MAX_VALUE)
        }
    }
}
