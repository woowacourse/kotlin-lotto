package domain

import kotlin.math.floor

data class LottoResult(private val result: Map<Rank, Int>) : Map<Rank, Int> by result {
    init {
        require(result.values.sum() > 0) { ERROR_COUNT_OF_LOTTO_AT_LEAST_ONE }
    }

    fun getRateOfReturn(): Double {
        val total = result.values.sum() * LottoStore.LOTTO_PRICE
        return floor(result.map { getSumMoney(it).toDouble() / total }.sum() * 100) / 100
    }

    private fun getSumMoney(entry: Map.Entry<Rank, Int>): Int = entry.key.winningMoney * entry.value

    companion object {
        private const val ERROR_COUNT_OF_LOTTO_AT_LEAST_ONE = "적어도 하나 이상의 복권을 넣어야합니다."
        fun of(lottos: List<Lotto>, winningLotto: WinningLotto): LottoResult =
            LottoResult(
                Rank.values().associateWith { rank ->
                    lottos.count { rank == Rank.valueOf(winningLotto.getCountOfMatch(it), winningLotto.matchBonus(it)) }
                },
            )
    }
}
