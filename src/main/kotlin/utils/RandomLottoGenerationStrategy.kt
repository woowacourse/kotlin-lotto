package utils

import model.Amount
import model.Lotto
import model.LottoNumber

class RandomLottoGenerationStrategy(private val amount: Amount) : LottoGenerationStrategy {
    override fun generateLottos(): List<Lotto> {
        val lottos = mutableListOf<Lotto>()
        repeat(amount.money / LOTTO_TICKET_PRICE) {
            lottos.add(Lotto(generateNumbers()))
        }
        return lottos
    }

    private fun generateNumbers(): List<LottoNumber> =
        (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER)
            .toList()
            .shuffled()
            .take(LOTTO_COUNT)
            .sorted()
            .map {
                LottoNumber(it)
            }

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val LOTTO_COUNT = 6
        private const val LOTTO_TICKET_PRICE = 1000
    }
}
