package lotto.view

import lotto.domain.value.PurchaseQuantity

class OutputView {
    fun printPurchaseQuantity(purchaseQuantity: PurchaseQuantity) {
        println("${purchaseQuantity.quantity}개를 구매했습니다.")
    }
}