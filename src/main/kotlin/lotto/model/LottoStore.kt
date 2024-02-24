package lotto.model

import lotto.service.LottoNumberGenerator
import lotto.service.RandomLottoNumberGenerator

class LottoStore private constructor(val lottos: List<Lotto>) {
    fun calculateWinningStatistics(winningLotto: WinningLotto): WinningStatistics {
        val statistics =
            lottos
                .map { winningLotto.getLottoPrize(it) }
                .groupBy { it }
                .mapValues { it.value.size }
        return WinningStatistics(statistics)
    }

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
