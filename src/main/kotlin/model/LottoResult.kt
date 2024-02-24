package model

class LottoResult(private val lottoResult: Map<Rank, Int>) {
    val winningMoney: Long by lazy {
        lottoResult.map {
            val money: Long = it.key.winningMoney.toLong() * it.value
            money
        }.sum()
    }

    fun getNum(rank: Rank): Int = lottoResult[rank] ?: 0
}
