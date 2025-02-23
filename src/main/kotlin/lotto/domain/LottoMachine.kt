package lotto.domain

import lotto.generator.LottoRandomGenerator

class LottoMachine {
    val price: Int = LOTTO_PRICE

    fun buyLottoTickets(purchaseAmount: Int): List<Lotto> {
        val randomGenerator = LottoRandomGenerator()

        val lottoCount = getLottoCount(LottoPayment(purchaseAmount))
        val lottoTickets: MutableList<Lotto> = mutableListOf()

        repeat(lottoCount) {
            lottoTickets.add(Lotto(randomGenerator.getRandomNumberList()))
        }
        return lottoTickets.toList()
    }

    private fun getLottoCount(purchaseAmount: LottoPayment): Int {
        return purchaseAmount.toInt() / LOTTO_PRICE
    }

    companion object {
        const val LOTTO_PRICE: Int = 1_000
    }
}
