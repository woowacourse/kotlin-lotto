package domain.lotto.game

import domain.game.LottoGame
import domain.lotto.PurchasedLotto
import domain.lotto.WinningLotto
import domain.lotto.number.LottoNumber
import domain.money.Money
import domain.rank.Rank
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoGameTest {
    private lateinit var lottoGame: LottoGame

    @BeforeEach
    fun setUp() {
        val bonusNumber = LottoNumber(7)
        val winningLotto = WinningLotto((1..6).map { LottoNumber(it) }, bonusNumber)
        lottoGame = LottoGame(winningLotto, bonusNumber)
    }

    @Test
    fun `매치 결과와 투자 금액이 주어졌을 때, calculateIncomeRate 호출시, 수익률을 반환한다`() {
        val actual = lottoGame.calculateIncomeRate(
            mapOf(Rank.FIFTH_WITH_BONUS to 1),
            Money(14000)
        )
        Assertions.assertEquals(0.35, actual)
    }

    @Test
    fun `1등 로또가 1개 주어졌을 때, matchLottos 호출시, Rank의 FIRST를 하나 반환한다 `() {
        val actual = lottoGame.matchLottos(listOf(PurchasedLotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) })))
        val expected = mutableMapOf<Rank, Int>()
        expected.put(Rank.FIRST_WITH_BONUS, 1)
        Assertions.assertEquals(expected, actual)
    }
}
