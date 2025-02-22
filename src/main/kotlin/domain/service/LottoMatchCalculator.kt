package domain.service

import domain.model.Lotto
import domain.model.LottoResult
import domain.model.LottoTicket
import domain.model.Rank
import domain.model.WinningLotto

class LottoMatchCalculator(
    private val purchaseLotto: LottoTicket,
    private val winningLotto: WinningLotto,
) {
    fun calculate(): LottoResult {
        val lottoRanks = purchaseLotto.values.map { getRank(it) }
        return LottoResult(lottoRanks.groupingBy { it }.eachCount())
    }

    private fun getRank(lotto: Lotto): Rank {
        val buyLottoNumbers = lotto.numbers
        val winningLottoNumbers = winningLotto.lotto.numbers

        val lottoMatches = buyLottoNumbers.intersect(winningLottoNumbers.toSet()).size
        val isBonusMatched = winningLotto.bonusNumber.value in buyLottoNumbers
        val rank = Rank.valueOf(lottoMatches, isBonusMatched)
        return rank
    }
}
