package lotto.model

import lotto.view.ERROR_SIZE_OF_LOTTO

class Lotto(val lotto: List<LottoNumber>) {

    init {
        require(lotto.size == LOTTO_SIZE) { ERROR_SIZE_OF_LOTTO }
    }

    constructor(vararg numbers: Int) : this(numbers.map { LottoNumber.from(it) })

    fun getCountOfMatch(winningLotto: WinningLotto): Rank {
        val count = lotto.count { winningLotto.winningNumbers.isContained(it) }
        val isContainBonusNumber = isContained(winningLotto.bonusNumber)

        return Rank.values().find {
            it.countOfMatch == count && it.isContainBonus == isContainBonusNumber
        } ?: Rank.MISS
    }

    fun isContained(findNumber: LottoNumber): Boolean = lotto.contains(findNumber)
    override fun toString(): String = lotto.map { it.number }.joinToString(", ")

    companion object {
        private const val LOTTO_SIZE = 6
    }
}
