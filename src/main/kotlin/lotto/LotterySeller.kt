package lotto

import lotto.domain.Lottery
import lotto.domain.LotteryGenerator
import lotto.domain.PurchaseAmount

class LotterySeller {

    fun generateLotteries(purchaseAmount: PurchaseAmount): List<Lottery> {
        val generator: LotteryGenerator = LotteryGenerator()
        val quantity: Int = purchaseAmount.getPurchaseQuantity()
        return generator.generateLotteries(quantity)
    }
}
