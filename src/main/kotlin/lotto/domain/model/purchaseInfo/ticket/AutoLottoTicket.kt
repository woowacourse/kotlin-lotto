package lotto.domain.model.purchaseInfo.ticket

import lotto.domain.model.purchaseInfo.LottoNumber

class AutoLottoTicket : LottoTicket {
    private val _lottoNumbers = createRawAutoLottoNumbers().map { LottoNumber(it) }.toSet()

    private fun createRawAutoLottoNumbers(): List<Int> = LottoNumber.LOTTO_RANGE.shuffled().take(LottoTicket.LOTTO_TICKET_SIZE)

    override val lottoNumbers: Set<LottoNumber>
        get() = _lottoNumbers
}
