package lotto.domain

class LottoResults(
    private val value: List<LottoResult>,
) {
    fun getTally(): Map<LottoResult, Int> {
        val tally = LottoResult.entries.associateWith { 0 }.toMutableMap()
        value.forEach { lottoResult: LottoResult ->
            tally[lottoResult] = tally.getValue(lottoResult) + 1
        }
        return tally.toMap()
    }

    fun getProfitRate(): Double {
        val profit: Long =
            value.sumOf { lottoResult: LottoResult ->
                lottoResult.prize.toLong()
            }
        return profit / (value.size * Lotto.PRICE).toDouble()
    }

    companion object {
        fun from(
            winningLotto: WinningLotto,
            userLottos: Lottos,
        ): LottoResults =
            LottoResults(
                userLottos.value.map { userLotto: Lotto ->
                    LottoResult.from(winningLotto, userLotto)
                },
            )
    }
}
