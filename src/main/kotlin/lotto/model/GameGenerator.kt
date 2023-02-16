package lotto.model

import lotto.entity.Game
import lotto.entity.LottoPrice
import lotto.entity.PurchaseMoney

class GameGenerator(private val lottoGenerator: LottoGenerator) {
    fun generate(purchaseMoney: PurchaseMoney, lottoPrice: LottoPrice): Game {
        val count = purchaseMoney.value / lottoPrice.value
        return Game(
            (0 until count).map {
                lottoGenerator.generate()
            }
        )
    }
}
