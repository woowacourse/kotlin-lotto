package lotto.model

class LottoStore private constructor(private val lottos: List<Lotto>) {
    val size = lottos.size

    fun calculateWinningStatistics(winningLotto: WinningLotto): WinningStatistics {
        val statistics =
            lottos
                .map { winningLotto.getLottoPrize(it) }
                .groupBy { it }
                .mapValues { it.value.size }
        return WinningStatistics(statistics)
    }

    fun forEach(action: (Lotto) -> Unit) = lottos.forEach(action)

    companion object {
        fun create(
            purchaseInfo: PurchaseInfo,
            lottoNumberGenerator: LottoNumberGenerator = RandomLottoNumberGenerator,
        ): LottoStore {
            val lottos = List(purchaseInfo.amount) { Lotto(lottoNumberGenerator.generate()) }
            return LottoStore(lottos)
        }
    }
}
