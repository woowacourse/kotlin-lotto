package domain

import common.convertToLottoNumberSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoStatisticsTest {

    @Test
    fun `Ticket을 넘겨 받아서 당첨 번호와 비교한다`() {
        val ticket = Ticket(
            listOf(
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(1, 2, 3, 4, 5, 13),
                Lotto(1, 2, 3, 4, 5, 9),
            )
        )
        val winningNumber = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(13)
        val winningLotto = WinningLotto(Lotto(winningNumber.convertToLottoNumberSet()), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val lottoResult: LottoResult = lottoStatistics.matchTicket(ticket)

        assertThat(lottoResult[Rank.FIRST]).isEqualTo(2)
        assertThat(lottoResult[Rank.SECOND]).isEqualTo(1)
        assertThat(lottoResult[Rank.THIRD]).isEqualTo(1)
    }

    @Test
    fun `총 수익률을 계산한다`() {
        val winResult: MutableMap<Rank, Int> = Rank.values().associateWith { 0 }.toMutableMap()
        winResult[Rank.FIRST] = 0
        winResult[Rank.SECOND] = 0
        winResult[Rank.THIRD] = 0
        winResult[Rank.FOURTH] = 0
        winResult[Rank.FIFTH] = 1
        winResult[Rank.MISS] = 13

        val winningNumber = setOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(13)
        val winningLotto = WinningLotto(Lotto(winningNumber.convertToLottoNumberSet()), bonusNumber)
        val lottoStatistics = LottoStatistics(winningLotto)
        val purchaseLottoMoney = PurchaseLottoMoney(14000)
        val result = lottoStatistics.yield(LottoResult(winResult), purchaseLottoMoney)
        val expected = "0.35"
        assertThat(result).isEqualTo(expected)
    }
}
