package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoPurchaseInfo

class ManualLottoMachine(private val manualNumbers: List<List<Int>> = emptyList()) : LottoMachine {
    override fun publishLottoTickets(lottoPurchaseInfo: LottoPurchaseInfo): List<Lotto> {
        if (manualNumbers.isEmpty()) return emptyList()

        return manualNumbers.map { number -> Lotto.from(number.sorted()) }
            .also { if (it.size != lottoPurchaseInfo.manualLottoCount) throw IllegalArgumentException("[ERROR] 수동 로또의 개수가 일치하지 않습니다.") }
    }
}
