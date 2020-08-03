package lotto.domain

private const val FROM = 0

class RandomStrategy : GenerateStrategy {
    override fun generate(): LottoTicket {
        val lottoNumbers: List<Int> = (LOTTO_FIRST_NUMBER..LOTTO_LAST_NUMBER).shuffled()
            .subList(FROM, LOTTO_TICKET_SIZE)

        return LottoTicket.from(lottoNumbers.map { LottoNumber.from(it) })
    }
}
