package model

object RandomLottoTicketGenerator : LottoTicketGenerator {
    override fun generate(): LottoTicket {
        val lottoNumbers =
            LottoNumber.RANGE.shuffled()
                .take(LottoTicket.SIZE)
                .map { LottoNumber(it) }
        return LottoTicket(lottoNumbers)
    }
}
