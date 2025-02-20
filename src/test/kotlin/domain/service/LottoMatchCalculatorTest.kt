package domain.service

import domain.model.BonusNumber
import domain.model.Lotto
import domain.model.PurchaseLotto
import domain.model.Rank
import domain.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMatchCalculatorTest {
    @Test
    fun `한 장의 Lotto에 대한 당첨 결과 구하기`() {
        val purchaseLotto =
            PurchaseLotto(
                listOf(
                    Lotto(listOf(1, 2, 3, 4, 5, 6)),
                ),
            )
        val winningLotto = WinningLotto(Lotto(listOf(1, 3, 4, 5, 6, 7)), BonusNumber(2))
        val calculator = LottoMatchCalculator(purchaseLotto, winningLotto)
        val result = calculator.calculate()

        assertThat(result[Rank.SECOND]).isEqualTo(1)
    }
}
