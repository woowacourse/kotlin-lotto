package lotto.view

import lotto.domain.model.Lotto
import lotto.domain.value.PurchaseQuantity

class OutputView {
    fun printPurchaseQuantity(purchaseQuantity: PurchaseQuantity) {
        println("${purchaseQuantity.quantity}개를 구매했습니다.")
    }

    fun printLottos(lottos: List<Lotto>) {
        lottos.forEach {
            println(it.lottoNumbers.map { it.number })
        }
    }
}
