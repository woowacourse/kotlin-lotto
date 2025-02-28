package lotto.domain.model.lottomachine

import lotto.domain.model.Lotto
import lotto.domain.model.LottoPayInfo
import lotto.domain.valueobject.LottoNumber

interface LottoMachine {
    fun generateLottoBundle(
        payInfo: LottoPayInfo,
        manualTicketsNumbers: List<List<LottoNumber>>? = null,
    ): List<Lotto>
}
