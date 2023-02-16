package domain.model.lotto

import domain.model.LottoResult
import domain.model.WinningNumbers

class PurchasedLottos(val lottos: List<Lotto>) {

    fun getTotalLottoResults(winningNumbers: WinningNumbers) = lottos.map { lotto ->
        lotto.getLottoResult(winningNumbers)
    }.filter { lottoResult ->
        lottoResult != LottoResult.MISS
    }
}
