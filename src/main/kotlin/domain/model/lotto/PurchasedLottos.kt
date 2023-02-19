package domain.model.lotto

import domain.model.LottoResult
import domain.model.WinningLotto

class PurchasedLottos(val lottos: List<Lotto>) {

    fun getTotalLottoResults(winningLotto: WinningLotto) = lottos.map { lotto ->
        lotto.getLottoResult(winningLotto)
    }.filter { lottoResult ->
        lottoResult != LottoResult.MISS
    }
}
