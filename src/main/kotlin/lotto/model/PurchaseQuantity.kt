package lotto.model

class PurchaseQuantity(purchasePrice: String) {

    val amount: Int

    init {
        require(purchasePrice.isValidDigit() && purchasePrice.isMoreThanMin() && purchasePrice.divideByLottoPrice()) {
            "올바른 구매 금액을 입력해 주세요."
        }
        amount = purchasePrice.toInt() / 1_000
    }

    private fun String.isValidDigit() = toIntOrNull() != null

    private fun String.isMoreThanMin() = toInt() >= 1000

    private fun String.divideByLottoPrice() = toInt() % 1000 == 0
}
