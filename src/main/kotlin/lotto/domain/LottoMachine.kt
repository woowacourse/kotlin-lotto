package lotto.domain

import lotto.generator.LottoRandomGenerator

class LottoMachine {
    val price: Int = LOTTO_PRICE

    fun buyLottoTickets(
        purchaseAmount: LottoPayment,
        lottoRandomGenerator: LottoRandomGenerator,
    ): List<Lotto> {
        val lottoCount = getLottoCount(purchaseAmount)
        val lottoTickets: MutableList<Lotto> = mutableListOf()

        repeat(lottoCount) {
            lottoTickets.add(lottoRandomGenerator.getRandomNumberList())
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
