package lotto.model

import lotto.view.ERROR_DUPLICATE_NUMBER
import lotto.view.ERROR_SIZE_OF_LOTTO

class Lotto(val lotto: List<LottoNumber>) {

    init {
        require(lotto.size == LOTTO_SIZE) { ERROR_SIZE_OF_LOTTO }
        require(hasNoDuplicateNumber()) { ERROR_DUPLICATE_NUMBER }
    }

    constructor(vararg numbers: Int) : this(numbers.map(::LottoNumber))

    fun getCountOfMatch(winningLotto: WinningLotto): Rank {
        val count = lotto.count { winningLotto.winningNumbers.isContained(it) }
        if (count == Rank.SECOND.countOfMatch) {
            return getRankByBonusNumber(winningLotto)
        }
        return Rank.values().find {
            it.countOfMatch == count
        } ?: Rank.MISS
    }

    fun isContained(findNumber: LottoNumber): Boolean = lotto.contains(findNumber)

    private fun getRankByBonusNumber(winningLotto: WinningLotto) =
        if (isContained(winningLotto.bonusNumber)) Rank.SECOND else Rank.THIRD

    private fun hasNoDuplicateNumber(): Boolean = lotto.size == lotto.map { it.number }.toSet().size

    override fun toString(): String = lotto.map { it.number }.joinToString(", ")

    companion object {
        private const val LOTTO_SIZE = 6
    }
}
