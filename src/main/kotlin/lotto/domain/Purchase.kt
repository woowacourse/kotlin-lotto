package lotto.domain

class Purchase(
    val lottoPrice: Int,
) {
    init {
        require(lottoPrice >= LOTTO_PRICE) { LOTTO_PRICE_ERROR_MESSAGE }
    }

    fun calculateAmountOfLottos(): LottoAmount {
        val amount = lottoPrice / LOTTO_PRICE
        require(amount > 0) { LOTTO_TOTAL_AMOUNT_ERROR_MESSAGE }
        return LottoAmount(amount)
    }

    fun getPrice(): Int {
        return lottoPrice - (lottoPrice % LOTTO_PRICE)
    }

    companion object {
        const val LOTTO_PRICE = 1000
        const val LOTTO_PRICE_ERROR_MESSAGE = "구입 금액은 1000원 이상이어야 합니다"
        const val LOTTO_TOTAL_AMOUNT_ERROR_MESSAGE = "구입한 로또의 수는 1개 이상이어야 합니다"
    }
}
