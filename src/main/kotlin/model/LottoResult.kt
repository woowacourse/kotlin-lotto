package model

class LottoResult(private val lottoResult: Map<Rank, Int>) {
    val winningMoney: Int by lazy {
        lottoResult.map {
            it.key.winningMoney * it.value
        }.sum()
    }

    fun getNum(rank: Rank): Int = lottoResult[rank] ?: 0
}
