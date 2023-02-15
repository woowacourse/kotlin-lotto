package lotto.model

import lotto.view.ERROR_DUPLICATE_NUMBER
import lotto.view.ERROR_OUT_OF_RANGE
import lotto.view.ERROR_SIZE_OF_LOTTO

class Lotto(val lotto: List<Int>) {

    val size: Int
        get() = lotto.size

    init {
        require(lotto.size == LOTTO_SIZE) { ERROR_SIZE_OF_LOTTO }
        require(hasNoDuplicateNumber()) { ERROR_DUPLICATE_NUMBER }
        require(isEveryNumberInRange()) { ERROR_OUT_OF_RANGE }
    }

    fun getCountOfMatch(winningLotto: WinningLotto): Rank {
        val count = lotto.count { winningLotto.winningNumbers.lotto.contains(it) }
        if (count == Rank.SECOND.countOfMatch) {
            return getRankByBonusNumber(winningLotto)
        }
        return Rank.values().find {
            it.countOfMatch == count
        } ?: Rank.MISS
    }

    private fun getRankByBonusNumber(winningLotto: WinningLotto) =
        if (lotto.contains(winningLotto.bonusNumber)) Rank.SECOND else Rank.THIRD

    private fun hasNoDuplicateNumber(): Boolean {
        return lotto.size == lotto.distinct().size
    }

    private fun isEveryNumberInRange(): Boolean {
        var result = true
        lotto.forEach {
            result = result and isInRange(it)
        }
        return result
    }

    private fun isInRange(number: Int): Boolean {
        return number in 1..45
    }

    companion object {
        private const val LOTTO_SIZE = 6
    }
}
