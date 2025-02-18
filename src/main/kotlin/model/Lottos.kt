package model

import Rank

class Lottos(private val lottos: List<Lotto>) {
    fun getLottoRanks(winningLotto: WinningLotto): Map<Rank, Int> {
        val result = mutableMapOf<Rank, Int>()
        lottos.forEach {
            val rank = winningLotto.getRank(it)
            result[rank] = (result[rank]?.plus(1)) ?: 1
        }
        return result
    }
}
