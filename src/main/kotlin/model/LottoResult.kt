package model

import domain.Rank

class LottoResult(
    private val bundleOfLotto: List<Lotto>,
    private val winningNumber: WinningNumber,
) {
    private val _result: MutableMap<Rank, Int> = mutableMapOf()
    val result: Map<Rank, Int> get() = _result.toMap()

    init {
        readyLottoResult()
    }

    fun getProfitRate(): Double {
        var profit = DOUBLE_ZERO
        var countOfTicket = DOUBLE_ZERO
        _result.forEach { (rank, count) ->
            profit += (rank.winningMoney * count)
            countOfTicket += count
        }

        return (profit / (countOfTicket * ONE_LOTTO_PRICE))
    }

    private fun readyLottoResult() {
        bundleOfLotto.forEach { lotto ->
            val matchOfCount = compareLottoNumber(lotto, winningNumber.lotto)
            val isMatchBonus = checkBonusNumber(lotto, winningNumber.bonusNumber)
            val rank = Rank.valueOf(matchOfCount, isMatchBonus)

            updateRankCount(rank)
        }
    }

    private fun updateRankCount(rank: Rank) {
        _result[rank] = (_result[rank] ?: NULL) + 1
    }

    private fun compareLottoNumber(lotto: Lotto, winningNumber: Lotto) = lotto.getMatchOfNumber(winningNumber)

    private fun checkBonusNumber(lotto: Lotto, winningNumber: LottoNumber) = lotto.isMatchBonus(winningNumber)

    companion object {
        private const val NULL = 0
        private const val DOUBLE_ZERO = 0.00
        private const val ONE_LOTTO_PRICE = 1000
    }
}
