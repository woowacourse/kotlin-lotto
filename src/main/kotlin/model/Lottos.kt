package lotto.model

import model.Lotto
import model.LottoNumber

class Lottos(val lottos: List<Lotto>) {
    fun winningResult(
        winningNumbers: Lotto,
        bonusNumber: LottoNumber,
    ): Map<WinningRank, Int> {
        val winningResult =
            lottos.groupBy { lotto ->
                val matchCount = lotto.getMatchCount(winningNumbers.lottoNumbers)
                val matchBonus = lotto.getMatchBonus(bonusNumber.number)
                WinningRank.findByMatchCount(matchCount, matchBonus)
            }.mapValues { (_, value) -> value.size }
        return winningResult
    }
}
