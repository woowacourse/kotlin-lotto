package lotto.domain

import lotto.util.Rank

class LottoCalculator(private val winningNumber: Lotto, private val bonusNumber: LottoNumber) {
    fun matchLottos(lottos: List<Lotto>): Map<Rank, Int> {
        val matchResult = lottos.map { matchLotto(it) }
        return Rank.entries.associateWith { rank -> matchResult.count { it == rank } }
    }

    private fun matchLotto(lotto: Lotto): Rank {
        val count: Int = compareLotto(lotto.numbers)
        val bonus: Boolean = checkBonusNumber(lotto.numbers)
        return Rank.getRankState(count, bonus)
    }

    fun compareLotto(lottos: List<LottoNumber>): Int {
        return lottos.intersect(winningNumber.numbers).size
    }

    fun checkBonusNumber(lottos: List<LottoNumber>): Boolean {
        return lottos.contains(bonusNumber)
    }

    fun calculatePrize(winningStats: Map<Rank, Int>): Long {
        var totalPrize: Long = DEFAULT_VALUE.toLong()

        winningStats.forEach { (state, count) ->
            totalPrize += state.price * count
        }

        return totalPrize
    }

    fun calculateProfit(
        totalPrize: Long,
        purchaseAmount: Int,
    ): Double {
        return totalPrize / purchaseAmount.toDouble()
    }

    companion object {
        private const val DEFAULT_VALUE = 0
        private const val INCREASED_VALUE = 1
    }
}
