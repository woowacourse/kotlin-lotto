package lotto.model

class PurchaseInfo(purchasePrice: String, private val lottoPrice: Int = LOTTO_PRICE) {
    val price: Int
    val amount: Int

    init {
        require(purchasePrice.isValidDigit() && purchasePrice.isMoreThanMin() && purchasePrice.divideByLottoPrice()) {
            INVALID_PURCHASE_PRICE
        }
        price = purchasePrice.toInt()
        amount = price / lottoPrice
    }

    private fun String.isValidDigit() = toIntOrNull() != null

    private fun String.isMoreThanMin() = toInt() >= lottoPrice

    private fun String.divideByLottoPrice() = toInt() % lottoPrice == 0

    companion object {
        private const val LOTTO_PRICE = 1000
        private const val INVALID_PURCHASE_PRICE = "올바른 구매 금액을 입력해 주세요."
    }
}
