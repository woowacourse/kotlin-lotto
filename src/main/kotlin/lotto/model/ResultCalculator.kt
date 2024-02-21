package lotto.model

object ProfitRatioCalculator {

    fun calculatePrize(lottoStore: LottoStore, winningLotto: Lotto, bonusNumber: Int) =
        lottoStore.lottos
            .map { lotto -> lotto.compare(winningLotto, bonusNumber) }
            .groupBy { it }
            .mapValues { it.value.size }
}
