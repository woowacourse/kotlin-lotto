package lotto.model

import kotlin.collections.mutableListOf

class LottoMachine(private val amount: Amount) {
    private val numbers: List<Int> = (LottoNumber.MIN_VALUE..LottoNumber.MAX_VALUE).toList()

    fun publishLottoTickets(): List<Lotto> {
        val lottoTickets = mutableListOf<Lotto>()
        repeat(amount.getLottoQuantity()) {
            val lotto = publishLotto()
            lottoTickets.add(lotto)
        }
        return lottoTickets
    }

    private fun publishLotto(): Lotto {
        val lottoNumbers = numbers.shuffled().take(LOTTO_NUMBER_AMOUNT).sorted().map { LottoNumber(it) }
        return Lotto(lottoNumbers)
    }

    companion object {
        private const val LOTTO_NUMBER_AMOUNT = 6
    }
}
