package lotto.model

class AutoLottoCountCalculator(
    private val purchasePrice: Int,
    private val manualLottoCount: Int,
) {
    fun calculate(): Int {
        require(purchasePrice / PRICE_OF_LOTTO_TICKET - manualLottoCount >= 0) { "자동로또개수 오류" }
        return purchasePrice / PRICE_OF_LOTTO_TICKET - manualLottoCount
    }

    companion object {
        const val PRICE_OF_LOTTO_TICKET = 1000
    }
}
