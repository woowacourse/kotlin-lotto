package lotto.model

object RandomLottoTicketGenerator : LottoTicketGenerator {
    override fun generate(): LottoTicket {
        val lottoNumbers =
            LottoNumber.RANGE.shuffled()
                .take(LottoTicket.SIZE)
                .map { LottoNumber.of(it) }
        return LottoTicket(lottoNumbers)
    }
}
