package lotto.domain

import lotto.constants.Rank

class UserLotto(private val lottoNumbers: List<Lotto>) {
    fun calculateTotalRank(winningNumbers: WinningNumbers): List<Rank> {
        val ranks = mutableListOf<Rank>()
        lottoNumbers.forEach { lotto ->
            ranks.add(
                Rank.valueOf(
                    lotto.countMatchingNumbers(winningNumbers.winningLotto),
                    lotto.checkMatchingBonusNumber(winningNumbers.bonusNumber)
                )
            )
        }
        return ranks
    }

    fun calculateCount(): Int {
        return lottoNumbers.size
    }
}
