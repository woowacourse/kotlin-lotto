package lotto.domain.model.lottomachine

import lotto.domain.model.Lotto
import lotto.domain.value.LottoNumber
import lotto.domain.value.LottoPayInfo

class ManualLottoMachine : LottoMachine {
    override fun generateLottoBundle(
        payInfo: LottoPayInfo,
        manualTicketsNumbers: List<List<LottoNumber>>?,
    ): List<Lotto> = manualTicketsNumbers?.map { Lotto.createSelfByManualLottoNumbers(it) } ?: emptyList()
}
