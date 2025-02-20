package domain.service

import domain.model.Lotto
import domain.model.LottoResult
import domain.model.PurchaseLotto
import domain.model.Rank
import domain.model.WinningLotto

class LottoMatchCalculator(
    private val purchaseLotto: PurchaseLotto,
    private val winningLotto: WinningLotto,
) {
    fun calculate(): LottoResult {
        val rank = Rank.rankMap().toMutableMap()
        purchaseLotto.values.forEach {
            rank[getRank(it)] = rank.getOrDefault(getRank(it), 0) + 1
        }
        return LottoResult(rank)
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
