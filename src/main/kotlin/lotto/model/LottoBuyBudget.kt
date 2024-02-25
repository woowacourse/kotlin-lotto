package lotto.model

class LottoBuyBudget(lottoBuyPrice: Int, val lottoPrice: Int = Lotto.LOTTO_PRICE) {
    var lottoBuyPrice = lottoBuyPrice
        private set

    init {
        require(lottoBuyPrice >= lottoPrice) { PRICE_ERROR_MESSAGE }
    }

    fun getTotalLottoBuyCount() = lottoBuyPrice / lottoPrice

    fun subtractLottoBuyPrice(minus: Int) {
        lottoBuyPrice -= minus
    }

    companion object {
        private const val PRICE_ERROR_MESSAGE = "구입 금액은 로또 1장의 가격 이상이여야합니다."
    }
}
