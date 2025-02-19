package domain.service

import domain.model.BonusNumber
import domain.model.Lotto
import domain.model.PurchaseLotto
import domain.model.WinningLotto
import org.junit.jupiter.api.Test

class LottoMatchCalculatorTest {
    @Test
    fun `로또 매칭 결과 테스트`() {
        val purchaseLotto =
            PurchaseLotto(
                listOf(
                    Lotto(listOf(1, 2, 3, 4, 5, 6)),
                    Lotto(listOf(1, 2, 3, 4, 5, 7)),
                    Lotto(listOf(1, 2, 3, 4, 8, 7)),
                    Lotto(listOf(1, 2, 3, 9, 8, 7)),
                    Lotto(listOf(1, 2, 10, 9, 8, 7)),
                    Lotto(listOf(1, 11, 10, 9, 8, 7)),
                    Lotto(listOf(12, 11, 10, 9, 8, 7)),
                ),
            )
        val winningLotto = WinningLotto(Lotto(listOf(1, 3, 4, 5, 6, 7)), BonusNumber(2))
        val calculator = LottoMatchCalculator(purchaseLotto, winningLotto)
        val result = calculator.calculate()
    }
}
