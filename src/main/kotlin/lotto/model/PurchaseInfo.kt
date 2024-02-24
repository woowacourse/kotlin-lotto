package lotto.model

class PurchaseInfo(val price: Int) {
    val amount: Int

    init {
        require(price.divideByLottoPrice()) { INVALID_DIVIDE_LOTTO_PRICE }
        amount = price / Lotto.PRICE
    }

    private fun Int.divideByLottoPrice() = this % Lotto.PRICE == 0

    companion object {
        private const val INVALID_DIVIDE_LOTTO_PRICE = "${Lotto.PRICE}원 단위로 입력해 주세요."
    }
}
