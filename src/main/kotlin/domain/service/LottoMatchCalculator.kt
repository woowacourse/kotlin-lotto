package domain.service

import domain.model.Lotto
import domain.model.LottoResult
import domain.model.PurchaseLotto
import domain.model.Rank
import domain.model.WinningLotto

class LottoMatchCalculator(
    val purchaseLotto: PurchaseLotto,
    val winningLotto: WinningLotto,
) {
    fun calculate() {
        val result = LottoResult(emptyMap())

        purchaseLotto.values.forEach {
            val rank = getRank(it)
        }
    }

    fun getRank(lotto: Lotto): Rank {
        val buyLottoNumbers = lotto.numbers
        val winningLottoNumbers = winningLotto.lotto.numbers

        val lottoMatches = buyLottoNumbers.intersect(winningLottoNumbers).size
        val isBonusMatched = winningLotto.bonusNumber.value in buyLottoNumbers
        val rank = Rank.valueOf(lottoMatches, isBonusMatched)
        return rank
    }
}
