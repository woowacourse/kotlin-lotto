package model

class LottoResult(private val lottoResult: Map<Rank, Int>) {
    fun setNullZero(rank: Rank): Int = lottoResult[rank] ?: 0
}
