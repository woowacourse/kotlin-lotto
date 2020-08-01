package lotto.domain

import lotto.domain.LottoNumber.Companion.LOTTO_FIRST_NUMBER
import lotto.domain.LottoNumber.Companion.LOTTO_LAST_NUMBER
import lotto.domain.LottoTicket.Companion.LOTTO_TICKET_SIZE

private const val INIT = 1

class RandomStrategy : GenerateStrategy {
    override fun generate(): LottoTicket {
        val lottoNumbers: MutableList<LottoNumber> = mutableListOf()
        for (i in (INIT..LOTTO_TICKET_SIZE)) {
            lottoNumbers.add(LottoNumber.of((LOTTO_FIRST_NUMBER..LOTTO_LAST_NUMBER).random()))
        }
        return LottoTicket(lottoNumbers)
    }
}
