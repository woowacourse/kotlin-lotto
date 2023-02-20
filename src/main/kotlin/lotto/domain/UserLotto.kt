package lotto.domain

class UserLotto(private val lottoNumbers: List<Lotto>) {
    fun calculateTotalRank(winningNumbers: WinningNumbers): List<Rank> {
        val ranks = mutableListOf<Rank>()
        lottoNumbers.forEach { lotto ->
            Rank.valueOf(
                lotto.countMatchingNumbers(winningNumbers.winningLotto),
                lotto.checkMatchingBonusNumber(winningNumbers.bonusNumber)
            )?.let {
                ranks.add(it)
            }
        }
        return ranks
    }

    fun calculateCount(): Int {
        return lottoNumbers.size
    }
}
