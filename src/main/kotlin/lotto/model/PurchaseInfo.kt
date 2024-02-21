package lotto.model

class PurchaseInfo(purchasePrice: String) {

    val price: Int
    val amount: Int

    init {
        require(purchasePrice.isValidDigit() && purchasePrice.isMoreThanMin() && purchasePrice.divideByLottoPrice()) {
            "올바른 구매 금액을 입력해 주세요."
        }
        price = purchasePrice.toInt()
        amount = price / 1_000
    }

    private fun String.isValidDigit() = toIntOrNull() != null

    private fun String.isMoreThanMin() = toInt() >= 1000

    private fun String.divideByLottoPrice() = toInt() % 1000 == 0
}
