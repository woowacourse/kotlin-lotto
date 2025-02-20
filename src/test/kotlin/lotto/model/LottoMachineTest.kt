package lotto.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    @ValueSource(ints = [-1, 0])
    @ParameterizedTest
    fun `입력한 금액은 0원 이상만 가능하다`(amount: Int) {
        runCatching {
            LottoMachine(amount)
        }.onFailure { error ->
            assert(error.message?.contains("0원 이상의 금액") ?: false)
        }
    }

    @Test
    fun `입력한 금액이 1,000으로 나누어지지 않으면 실패한다`() {
        assertThrows<IllegalArgumentException> { LottoMachine(1001) }
    }

    @Test
    fun `구입 금액이 5000원이면 로또를 5개 반환한다`() {
        val amount = 5000
        val lottoMachine = LottoMachine(amount)
        val lottoQuantity = lottoMachine.getLottoQuantity()

        Assertions.assertEquals(5, lottoQuantity)
    }

    @Test
    fun `로또의 당첨 결과에 맞는 수익률을 소수점 둘째 자리까지 반환한다`() {
        val winningResult =
            mapOf(
                Rank.FIRST to 1,
                Rank.SECOND to 0,
                Rank.THIRD to 0,
                Rank.FOURTH to 0,
                Rank.FIFTH to 1,
                Rank.MISS to 100,
            )

        val totalAmount = winningResult.values.size * 1000
        val lottoMachine = LottoMachine(totalAmount)

        val totalProfit = (Rank.FIRST.winningMoney + Rank.FIFTH.winningMoney).toFloat()
        val expectedProfitRate = (totalProfit / totalAmount.toFloat())

        Assertions.assertEquals(expectedProfitRate, lottoMachine.getProfitRate(winningResult))
    }
}
