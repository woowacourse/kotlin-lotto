package lotto.domain.generator

import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket

class ManualLottoGenerator(
    private val manualInput: List<Int>,
) : LottoGenerator {
    override fun generateLotto(): LottoTicket = LottoTicket.create(manualInput.map { LottoNumber(it) })
}
