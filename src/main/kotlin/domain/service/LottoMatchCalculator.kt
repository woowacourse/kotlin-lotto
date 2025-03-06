package domain.service

import domain.model.Lotto
import domain.model.LottoResult
import domain.model.Rank
import domain.model.WinningLotto
import util.Mapper

class LottoMatchCalculator(
    private val purchaseLotto: List<Lotto>,
    private val winningLotto: WinningLotto,
) {
    fun calculate(): LottoResult {
        val resultMap = LottoResult.DEFAULT_RESULT.toMutableMap()

        purchaseLotto.forEach {
            val rank = getRank(it)
            resultMap[rank] = resultMap.getOrDefault(rank, 0) + 1
        }

        return LottoResult(resultMap)
    }

    private fun getRank(lotto: Lotto): Rank {
        val buyLottoNumbers = Mapper.toNumberList(lotto)
        val winningLottoNumbers = Mapper.toNumberList(winningLotto.lotto)

        val lottoMatches = buyLottoNumbers.intersect(winningLottoNumbers.toSet()).size
        val isBonusMatched = winningLotto.bonusNumber.value in buyLottoNumbers
        return Rank.valueOf(lottoMatches, isBonusMatched)
    }
}
