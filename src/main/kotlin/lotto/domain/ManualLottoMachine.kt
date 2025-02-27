package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoPurchaseInfo

class ManualLottoMachine(private val manualNumbers: List<List<Int>>) : LottoMachine {
    override fun publishLottoTickets(lottoPurchaseInfo: LottoPurchaseInfo): List<Lotto> {
        return manualNumbers.map { manualNumber ->
            Lotto.from(manualNumber.sorted())
        }
    }
}
