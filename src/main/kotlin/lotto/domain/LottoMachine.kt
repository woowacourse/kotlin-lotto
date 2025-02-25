package lotto.domain

import lotto.generator.LottoGenerator

class LottoMachine {
    val price: Int = LOTTO_PRICE

    fun buyLottoTickets(
        purchaseAmount: Int,
        lottoGenerator: LottoGenerator,
    ): List<Lotto> {
        require(purchaseAmount >= LOTTO_PRICE) { ERROR_UNDER_THOUSAND }
        val lottoCount = getLottoCount(purchaseAmount)
        val lottoTickets: MutableList<Lotto> = mutableListOf()
        repeat(lottoCount) {
            lottoTickets.add(lottoGenerator.generateLottoNumbers())
        }
        return lottoTickets.toList()
    }

    private fun getLottoCount(purchaseAmount: Int): Int {
        return purchaseAmount / LOTTO_PRICE
    }

    companion object {
        const val LOTTO_PRICE: Int = 1_000
        const val ERROR_UNDER_THOUSAND = "[ERROR] 최소 로또 구입 금액은 1,000원입니다."
    }
}
