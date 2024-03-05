package lotto.model

class LottoResult(private val lottoResult: Map<Rank, Int>) {
    val winningMoney by lazy {
        lottoResult.map {
            it.key.winningMoney * it.value
        }.sum()
    }

    fun getNum(rank: Rank): Int = lottoResult[rank] ?: 0
}
