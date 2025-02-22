package lotto.model

class LottoMachine {
    private val numbers: List<LottoNumber> = (LottoNumber.MIN_VALUE..LottoNumber.MAX_VALUE).map(::LottoNumber)

    fun publishLottoTickets(lottoQuantity: Int): List<Lotto> = List(lottoQuantity) { publishLotto() }

    private fun publishLotto(): Lotto =
        Lotto(
            numbers.shuffled()
                .take(LOTTO_NUMBER_COUNT)
                .sortedBy { it.value },
        )

    companion object {
        private const val LOTTO_NUMBER_COUNT = 6
    }
}
