package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    private val winningTicket by lazy {
        LottoTicket.from(listOf(1, 2, 3, 4, 5, 6))
    }
    private val bonusNumber by lazy {
        LottoNumber(7)
    }

    @Test
    fun `특정 등수에 당첨되지 않았을 경우 0으로 센다`() {
        val rankMap = emptyMap<Rank, Int>()
        val lottoResult = LottoResult(rankMap)
        val actual = lottoResult.getNum(Rank.THIRD)
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `특정 등수에 당첨됐는지 그 개수를 센다`() {
        val userLottoIterator =
            listOf(
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)),
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)),
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)),
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)),
            ).iterator()

        val lottoPurchase = LottoPurchase(4000) { userLottoIterator.next() }

        val userTickets = lottoPurchase.makeUserTickets()
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)
        val lottoResult = lottoWinning.makeLottoResult()

        val actual = lottoResult.getNum(Rank.FIRST)
        val expected = 4

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `1등 로또 100개의 당첨금을 합산한다`() {
        val lottoCount = 100
        val userLottoIterator =
            List(lottoCount) { LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)) }.iterator()
        val lottoPrice = LottoPurchase.PRICE_OF_LOTTO_TICKET

        val lottoPurchase = LottoPurchase(lottoPrice * lottoCount) { userLottoIterator.next() }

        val userTickets = lottoPurchase.makeUserTickets()
        val lottoWinning = LottoWinning(winningTicket, bonusNumber, userTickets)

        val actual = lottoWinning.makeLottoResult().winningMoney
        val expected = 200_000_000_000

        assertThat(actual).isEqualTo(expected)
    }
}
