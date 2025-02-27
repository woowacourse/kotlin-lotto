package lotto.domain

class LottoCalculator(
    val amountOfPurchase: Int,
    private val lottoPrice: Int = LOTTO_PRICE,
) {
    init {
        validateAmount()
    }

    fun calculateAmountOfLottos(): Int = amountOfPurchase / lottoPrice

    fun calculateAutoLottos(manualCount: Int): Int {
        require(manualCount in 0..calculateAmountOfLottos()) { "수동 로또 개수가 잘못되었습니다." }
        return calculateAmountOfLottos() - manualCount
    }

    private fun validateAmount() {
        require(amountOfPurchase % lottoPrice == 0) { "로또는 ${lottoPrice}원 단위로 구입해주세요" }
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
