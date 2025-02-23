package lotto.domain

import lotto.generator.LottoRandomGenerator

class LottoMachine {
    val price: Int = LOTTO_PRICE

    fun buyLottos(purchaseAmount: Int): List<Lotto> {
        val randomGenerator = LottoRandomGenerator()

        val lottoCount = getLottoCount(LottoPayment(purchaseAmount))
        val lottos: MutableList<Lotto> = mutableListOf()

        repeat(lottoCount) {
            lottos.add(Lotto(randomGenerator.getRandomNumberList()))
        }
        return lottos.toList()
    }

    fun getLottoCount(purchaseAmount: LottoPayment): Int {
        return purchaseAmount.toInt() / LOTTO_PRICE
    }

    companion object {
        const val LOTTO_PRICE: Int = 1_000
    }
}
