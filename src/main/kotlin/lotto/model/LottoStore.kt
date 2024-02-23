package lotto.model

import lotto.constants.LottoPrize
import lotto.service.LottoNumberGenerator
import lotto.service.RandomLottoNumberGenerator

class LottoStore private constructor(val lottos: List<Lotto>) {
    fun calculateWinningStatistics(winningLotto: WinningLotto): Map<LottoPrize, Int> =
        lottos
            .map { it.compare(winningLotto.lotto, winningLotto.bonusNumber) }
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
