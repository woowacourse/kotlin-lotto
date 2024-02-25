package lotto.model

class LottoBuyPrice(val buyPrice: Int, val lottoPrice: Int = Lotto.LOTTO_PRICE) {
    init {
        require(buyPrice >= lottoPrice) { PRICE_ERROR_MESSAGE }
    }

    fun getBuyCount() = buyPrice / lottoPrice

    companion object {
        private const val PRICE_ERROR_MESSAGE = "구입 금액은 로또 1장의 가격 이상이여야합니다."
    }
}
