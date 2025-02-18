package lotto.domain

import lotto.util.Rank

class LottoGame(private val winningNumber: List<Int>, private val bonusNumber: Int) {
    var winningStats: ArrayList<Int> = arrayListOf(0, 0, 0, 0, 0)

    fun getSameNumberCount(lottoNumber: List<Int>): Rank {
        return when (winningNumber.intersect(lottoNumber).size) {
            6 -> Rank.FIRST
            5 -> checkBonusNumber(lottoNumber)
            4 -> Rank.FOURTH
            3 -> Rank.FIFTH
            else -> Rank.NONE
        }
    }

    fun checkBonusNumber(number: List<Int>): Rank {
        if (number.contains(bonusNumber)) {
            return Rank.SECOND
        }
        return Rank.THIRD
    }

    fun matchLotto(lottos: List<Lotto>) {
        lottos.forEach { lotto ->
            val state = getSameNumberCount(lotto.numbers)
            winningStats[state.index]++
        }
    }

    fun calculatePrize(): Long {
        var totalPrize: Long = 0
        winningStats.forEachIndexed { index, i ->
            totalPrize += Rank.entries.find { it.index == index }?.price?.times(i)!!
        }
        return totalPrize
    }
}
