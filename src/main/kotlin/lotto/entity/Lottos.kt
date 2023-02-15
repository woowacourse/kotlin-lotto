package lotto.entity

import lotto.model.LottoGenerator

class Lottos(purchaseMoney: PurchaseMoney, lottoPrice: LottoPrice, lottoGenerator: LottoGenerator) {
    val value: List<Lotto>

    init {
        val count = purchaseMoney.value / lottoPrice.value
        value = (0 until count).map {
            lottoGenerator.generate()
        }
    }
}
