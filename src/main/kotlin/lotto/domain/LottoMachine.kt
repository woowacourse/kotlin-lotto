package lotto.domain

import lotto.generator.LottoGenerator

class LottoMachine(private val randomGenerator: LottoGenerator = LottoGenerator()) {
    val price: Int = LOTTO_PRICE

    fun buyLottos(purchaseAmount: Int): List<Lotto> {
        val lottoCount = getLottoCount(purchaseAmount)
        return List(lottoCount) { Lotto(randomGenerator.getRandomLottoNumbers(NUMBER_OF_LOTTO_NUMBER)) }
    }

    private fun getLottoCount(purchaseAmount: Int): Int {
        return purchaseAmount / LOTTO_PRICE
    }

    companion object {
        private const val LOTTO_PRICE: Int = 1_000
        private const val NUMBER_OF_LOTTO_NUMBER: Int = 6
    }
}
