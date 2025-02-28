package lotto.domain.model.lottomachine

import lotto.domain.model.Lotto
import lotto.domain.model.LottoPayInfo
import lotto.domain.valueobject.LottoNumber

class AutoLottoMachine : LottoMachine {
    override fun generateLottoBundle(
        payInfo: LottoPayInfo,
        manualTicketsNumbers: List<List<LottoNumber>>?,
    ): List<Lotto> = List(payInfo.autoLottoQuantity) { Lotto.createSelfRandomly() }
}
