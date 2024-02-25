package lotto.model

class LottoBuyBudget(availableFund: Int, val pricePerLotto: Int = Lotto.PRICE_PER_LOTTO) {
    var availableFunds = availableFund
        private set

    init {
        require(availableFund >= pricePerLotto) { PRICE_ERROR_MESSAGE }
    }

    fun getBuyableLottoCount() = availableFunds / pricePerLotto

    fun subtractAvailableFunds(amountToSubtract: Int) {
        availableFunds -= amountToSubtract
    }

    companion object {
        private const val PRICE_ERROR_MESSAGE = "구입 금액은 로또 1장의 가격 이상이여야합니다."
    }
}
