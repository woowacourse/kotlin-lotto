package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoPurchaseInfo

interface LottoMachine {
    fun publishLottoTickets(lottoPurchaseInfo: LottoPurchaseInfo): List<Lotto>
}
