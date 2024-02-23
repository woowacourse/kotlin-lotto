package lotto.model

import lotto.constants.LottoPrize
import lotto.service.LottoNumberGenerator
import lotto.service.RandomLottoNumberGenerator

class LottoStore private constructor(val lottos: List<Lotto>) {
    fun calculateWinningStatistics(winningLotto: WinningLotto): Map<LottoPrize, Int> =
        lottos
            .map { winningLotto.getLottoPrize(it) }
            .groupBy { it }
            .mapValues { it.value.size }

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
