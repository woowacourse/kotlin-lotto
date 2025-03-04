package lotto.domain.model

class ManualLottoGenerator(
    private val manualInput: List<Int>,
) : LottoGenerator {
    override fun generateLotto(): LottoTicket = LottoTicket.create(manualInput.map { LottoNumber(it) })
}
