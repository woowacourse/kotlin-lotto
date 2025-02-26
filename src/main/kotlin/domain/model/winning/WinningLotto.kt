package domain.model.winning

import domain.model.LottoMatchResult
import domain.model.Rank
import domain.model.lotto.Lotto
import domain.model.number.LottoNumber

class WinningLotto(
    private val winningNumbers: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        if (bonusNumber.value in winningNumbers.numbers.map { it.value }) {
            throw WinningLottoException.DuplicatedBonusNumberException()
        }
    }

    fun calculate(purchaseLotto: List<Lotto>): LottoMatchResult {
        val rankResult = Rank.Companion.rankMap().toMutableMap()
        purchaseLotto.forEach { lotto ->
            val rank = getRank(lotto.numbers)
            rankResult[rank] = rankResult.getOrDefault(rank, 0) + 1
        }
        return LottoMatchResult(rankResult)
    }

    private fun getRank(buyLotto: List<LottoNumber>): Rank {
        val winningLottoNumbers = winningNumbers.numbers

        val lottoMatches = buyLotto.intersect(winningLottoNumbers).size
        val isBonusMatched = buyLotto.contains(bonusNumber)

        val rank = Rank.Companion.valueOf(lottoMatches, isBonusMatched)
        return rank
    }
}
