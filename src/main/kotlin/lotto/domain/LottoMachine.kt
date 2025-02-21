package lotto.domain

import lotto.generator.RandomGenerator

class LottoMachine(private val randomGenerator: RandomGenerator = RandomGenerator()) {
    val price: Int = LOTTO_PRICE

    fun buyLottos(purchaseAmount: Int): List<Lotto> {
        val lottoCount = getLottoCount(purchaseAmount)
        return List(lottoCount) { Lotto(randomGenerator.getRandomNumberList()) }
    }

    private fun getLottoCount(purchaseAmount: Int): Int {
        return purchaseAmount / LOTTO_PRICE
    }

    companion object {
        const val LOTTO_PRICE: Int = 1_000
    }
}
