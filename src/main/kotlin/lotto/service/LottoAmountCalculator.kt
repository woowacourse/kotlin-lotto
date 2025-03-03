package lotto.service

class LottoAmountCalculator(
    val amountOfPurchase: Int,
    private val lottoPrice: Int = LOTTO_PRICE,
) {
    fun calculateAmountOfLottos(): Int = amountOfPurchase / lottoPrice

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
