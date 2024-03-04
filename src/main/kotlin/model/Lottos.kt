package lotto.model

import model.Lotto

class Lottos(val lottos: List<Lotto>) {
    fun winningResult(winningNumbers: WinningNumbers): Map<WinningRank, Int> {
        val winningResult =
            lottos.groupBy { lotto ->
                val matchCount = lotto.getMatchCount(winningNumbers.winningLotto.lottoNumbers)
                val matchBonus = lotto.getMatchBonus(winningNumbers.bonusNumber.number)
                WinningRank.findByMatchCount(matchCount, matchBonus)
            }.mapValues { (_, value) -> value.size }
        return winningResult
    }
}
