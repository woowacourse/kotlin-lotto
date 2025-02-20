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
        val result = LottoResult()
        purchaseLotto.values.forEach {
            result.result[getRank(it)] = result.result.getOrDefault(getRank(it), 0) + 1
        }
        return result
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
