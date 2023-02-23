package domain.result

import domain.lotto.PurchasedLotto
import domain.money.Money

data class LottoPurchaseResult(
    val change: Money,
    val purchasedLottos: List<PurchasedLotto>,
)
