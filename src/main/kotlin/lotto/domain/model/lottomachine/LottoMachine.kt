package lotto.domain.model.lottomachine

import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber
import lotto.domain.value.LottoPayInfo

interface LottoMachine {
    fun generateLottoBundle(
        payInfo: LottoPayInfo,
        manualTicketsNumbers: List<List<LottoNumber>>? = null,
    ): List<Lotto>
}
