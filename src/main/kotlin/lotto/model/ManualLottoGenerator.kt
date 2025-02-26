package lotto.model

class ManualLottoGenerator(
    private val numbers : List<Int>
) : LottoGenerator {
    override fun generateLotto(): LottoTicket = LottoTicket(numbers.map(::LottoNumber))
}