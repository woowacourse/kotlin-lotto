package lotto.domain

import lotto.generator.LottoGenerator

class LottoMachine(private val purchaseAmount: Int) {
    init {
        require(purchaseAmount >= LOTTO_PRICE) { ERROR_UNDER_THOUSAND }
    }

    private val lottoTickets: MutableList<Lotto> = mutableListOf()

    fun buyLottoTickets(
        lottoCount: Int,
        lottoGenerator: LottoGenerator,
    ) {
        repeat(lottoCount) {
            lottoTickets.add(lottoGenerator.generateLottoNumbers())
        }
    }

    fun getLottoTickets(): List<Lotto> {
        return lottoTickets.toList()
    }

    fun getAutoLottoCount(manualLottoCount: Int): Int {
        return getTotalLottoCount(purchaseAmount) - manualLottoCount
    }

    private fun getTotalLottoCount(purchaseAmount: Int): Int {
        return purchaseAmount / LOTTO_PRICE
    }

    companion object {
        const val LOTTO_PRICE: Int = 1_000
        const val ERROR_UNDER_THOUSAND = "[ERROR] 최소 로또 구입 금액은 1,000원입니다."
    }
}
