package lotto.model

import lotto.exception.ErrorCode.INVALID_PURCHASE_AMOUNT
import lotto.exception.ErrorCode.PURCHASE_AMOUNT_NOT_NATURAL_NUMBER
import lotto.exception.ExceptionsHandler.handleValidation

object LottoMachine {
    private const val LOTTO_SIZE = 6
    private val LOTTO_NUMBER_RANGE: IntRange = 1..45
    const val MIN_PRICE = 1_000

    fun createLottoBundle(price: String): LottoBundle {
        handleValidation(PURCHASE_AMOUNT_NOT_NATURAL_NUMBER) { price.toIntOrNull() != null }
        handleValidation(INVALID_PURCHASE_AMOUNT) { price.toIntOrNull()?.let { it >= MIN_PRICE } == true }

        val lottos = List(getNumberOfLottoTickets(price)) { randomLotto() }

        return LottoBundle(lottos)
    }

    private fun randomLotto(): Lotto = Lotto(LOTTO_NUMBER_RANGE.shuffled().take(LOTTO_SIZE).sorted().map { LottoNumber(it) }.toSet())

    fun getNumberOfLottoTickets(price: String): Int = price.toInt() / MIN_PRICE
}
