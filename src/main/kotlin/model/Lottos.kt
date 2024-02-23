package lotto.model

import model.Lotto
import model.LottoNumber

class Lottos(val lottos: MutableList<Lotto> = mutableListOf()) {
    fun add(lotto: Lotto) {
        lottos.add(lotto)
    }

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
