package model

fun interface LottoGenerator {
    fun generate(lotteryNumbers: List<List<Int>>): List<Lotto>
}

class AutoLottoGenerator : LottoGenerator {
    override fun generate(lotteryNumbers: List<List<Int>>): List<Lotto> {
        return lotteryNumbers.map { Lotto(*it.toIntArray()) }
    }
}
