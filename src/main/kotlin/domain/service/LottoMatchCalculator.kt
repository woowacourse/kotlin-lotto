package domain.service

import domain.model.Lotto
import domain.model.PurchaseLotto
import domain.model.Rank
import domain.model.WinningLotto

class LottoMatchCalculator(
    val purchaseLotto: PurchaseLotto,
    val winningLotto: WinningLotto,
) {
    fun calculate(): Map<Rank, Int> {
        val result: MutableMap<Rank, Int> =
            Rank.entries
                .reversed()
                .associateWith { 0 }
                .toMutableMap()
        result[getRank(purchaseLotto.values.first())] =
            result.getOrDefault(getRank(purchaseLotto.values.first()), 0) + 1

        return result
    }

    fun getRank(lotto: Lotto): Rank {
        val buyLottoNumbers = lotto.numbers
        val winningLottoNumbers = winningLotto.lotto.numbers

        val lottoMatches = buyLottoNumbers.intersect(winningLottoNumbers.toSet()).size
        val isBonusMatched = winningLotto.bonusNumber.value in buyLottoNumbers
        val rank = Rank.valueOf(lottoMatches, isBonusMatched)
        return rank
    }
}
