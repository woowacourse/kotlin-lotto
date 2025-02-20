package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResult(
    private val ranks: List<Rank>,
) {
    fun calculateProfit(): Double {
        val totalWinningMoney = ranks.sumOf { it.winningMoney }
        return totalWinningMoney / (ranks.size * 1_000).toDouble()
    }
}

class LottoScanner(
    private val winningNumbers: Lotto,
) {
    fun getResult(lottos: List<Lotto>): LottoResult {
        val result = lottos.map { getRank(it) }
        return LottoResult(result)
    }

    fun getRank(lotto: Lotto): Rank {
        val countOfMatch: Int = getCountOfMatch(lotto)
        val matchBonus: Boolean = getMatchBonus(lotto)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    fun getCountOfMatch(lotto: Lotto): Int = winningNumbers.getNumbers().intersect(lotto.getNumbers().toSet()).size

    fun getMatchBonus(lotto: Lotto): Boolean = lotto.getNumbers().contains(winningNumbers.getBonusNumber())
}

class LottoScannerTest {
    @Test
    fun `로또 당첨을 판단한다`() {
        val bonusNumber: LottoNumber = LottoNumber(7)

        val winningNumbers =
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )

        val lottoTicket =
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
            )

        val lottoScanner = LottoScanner(winningNumbers)
        val rank = lottoScanner.getRank(lottoTicket)

        assertThat(rank).isEqualTo(Rank.FIRST)
    }
}
