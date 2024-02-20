package lotto.model

class PurchaseQuantity(purchasePrice: Int) {

    val amount: Int = purchasePrice / 1_000

}
