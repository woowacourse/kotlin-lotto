package lotto.domain

import lotto.domain.LottoNumber.Companion.LOTTO_FIRST_NUMBER
import lotto.domain.LottoNumber.Companion.LOTTO_LAST_NUMBER
import lotto.domain.LottoNumber.Companion.of
import lotto.domain.LottoTicket.Companion.LOTTO_TICKET_SIZE

private const val FROM = 0

class RandomStrategy : GenerateStrategy {
    override fun generate(): LottoTicket {
        val lottoNumbers: List<Int> = (LOTTO_FIRST_NUMBER..LOTTO_LAST_NUMBER).shuffled()
            .subList(FROM, LOTTO_TICKET_SIZE)

        return LottoTicket(lottoNumbers.map { of(it) })
    }
}
