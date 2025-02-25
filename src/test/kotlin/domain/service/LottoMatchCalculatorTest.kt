package domain.service

import domain.model.BonusNumber
import domain.model.Lotto
import domain.model.Rank
import domain.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoMatchCalculatorTest {
    private lateinit var purchaseLotto: List<Lotto>
    private lateinit var purchaseOneLotto: List<Lotto>
    private lateinit var winningLotto: WinningLotto

    @BeforeEach
    fun setUp() {
        purchaseLotto =
            listOf(
                Lotto.lottoOf(1, 2, 3, 4, 5, 6),
                Lotto.lottoOf(1, 2, 3, 4, 5, 7),
                Lotto.lottoOf(1, 2, 3, 4, 8, 7),
                Lotto.lottoOf(1, 2, 3, 9, 8, 7),
                Lotto.lottoOf(1, 2, 10, 9, 8, 7),
                Lotto.lottoOf(1, 11, 10, 9, 8, 7),
                Lotto.lottoOf(12, 11, 10, 9, 8, 7),
            )
        purchaseOneLotto = listOf(Lotto.lottoOf(1, 2, 3, 4, 5, 6))
        winningLotto = WinningLotto(Lotto.lottoOf(1, 3, 4, 5, 6, 7), BonusNumber(2))
    }

    @Test
    fun `한 장의 Lotto에 대한 당첨 결과 구하기`() {
        val calculator = LottoMatchCalculator(purchaseOneLotto, winningLotto)
        val result = calculator.calculate().result

        assertThat(result[Rank.SECOND]).isEqualTo(1)
    }

    @Test
    fun `n장의 Lotto에 대한 당첨 결과 구하기`() {
        val calculator = LottoMatchCalculator(purchaseLotto, winningLotto)
        val result = calculator.calculate().result

        assertThat(result[Rank.SECOND]).isEqualTo(2)
        assertThat(result[Rank.FOURTH]).isEqualTo(1)
        assertThat(result[Rank.FIFTH]).isEqualTo(1)
        assertThat(result[Rank.MISS]).isEqualTo(3)
    }
}
