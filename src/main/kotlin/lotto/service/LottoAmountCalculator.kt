package lotto.service

class LottoAmountCalculator(
    val amountOfPurchase: Int,
    private val lottoPrice: Int = LOTTO_PRICE,
) {
    init {
        validatePurchaseAmount()
    }

    fun calculateAmountOfLottos(): Int = amountOfPurchase / lottoPrice

    private fun validatePurchaseAmount() {
        require(amountOfPurchase % lottoPrice == 0) { "로또는 ${lottoPrice}원 단위로 구입해주세요" }
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
