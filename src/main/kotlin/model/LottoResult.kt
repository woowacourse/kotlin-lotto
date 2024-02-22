package model

class LottoResult (private val lottoResult:Map<Rank, Int>){
    fun getNum(rank: Rank):Int = lottoResult[rank]?:0
}