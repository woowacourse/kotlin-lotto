package lotto.model

class PurchaseQuantity private constructor(purchasePrice: Int) {

    val amount: Int = purchasePrice / 1_000

    companion object {
        fun create(purchasePrice: String): PurchaseQuantity {
            require(purchasePrice.isValidDigit() && purchasePrice.isMoreThanMin() && purchasePrice.divideByLottoPrice()) {
                "올바른 구매 금액을 입력해 주세요."
            }
            return PurchaseQuantity(purchasePrice.toInt())
        }

        private fun String.isValidDigit() = toIntOrNull() != null

        private fun String.isMoreThanMin() = toInt() >= 1000

        private fun String.divideByLottoPrice() = toInt() % 1000 == 0
    }
}
