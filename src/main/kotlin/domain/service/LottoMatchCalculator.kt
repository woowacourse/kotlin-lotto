package domain.service

import domain.model.Lotto
import domain.model.LottoResult
import domain.model.Rank
import domain.model.WinningLotto
import util.LottoFactory

class LottoMatchCalculator(
    private val purchaseLotto: List<Lotto>,
    private val winningLotto: WinningLotto,
) {
    fun calculate(): LottoResult {
        val result = LottoResult()
        purchaseLotto.forEach {
            result.result[getRank(it)] = result.result.getOrDefault(getRank(it), 0) + 1
        }
        return result
    }

    private fun getRank(lotto: Lotto): Rank {
        val buyLottoNumbers = LottoFactory().extractionNumber(lotto)
        val winningLottoNumbers = LottoFactory().extractionNumber(winningLotto.lotto)

        val lottoMatches = buyLottoNumbers.intersect(winningLottoNumbers.toSet()).size
        val isBonusMatched = winningLotto.bonusNumber.value in buyLottoNumbers
        val rank = Rank.valueOf(lottoMatches, isBonusMatched)
        return rank
    }
}
