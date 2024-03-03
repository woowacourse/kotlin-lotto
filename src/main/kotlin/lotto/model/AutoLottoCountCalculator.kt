package lotto.model

class AutoLottoCountCalculator(
    private val purchasePrice: Int,
    private val manualLottoCount: Int,
) {
    fun calculate(): Int {
        return purchasePrice / PRICE_OF_LOTTO_TICKET - manualLottoCount
    }

    companion object {
        const val PRICE_OF_LOTTO_TICKET = 1000
    }
}
