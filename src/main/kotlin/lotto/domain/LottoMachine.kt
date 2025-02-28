package lotto.domain

import lotto.generator.LottoGenerator

class LottoMachine {
    fun buyLottos(lottoGenerator: LottoGenerator): List<Lotto> {
        return lottoGenerator.generateLotto()
    }

    fun getPurchasableLottoCount(purchaseAmount: LottoPurchaseAmount): Int {
        return purchaseAmount.money / LOTTO_PRICE
    }

    companion object {
        const val LOTTO_PRICE: Int = 1_000
    }
}
