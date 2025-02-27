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
            val rank = lotto.getRank(winningNumbers, bonusNumber)
            rankResult[rank] = rankResult.getOrDefault(rank, 0) + 1
        }
        return LottoMatchResult(rankResult)
    }
}
