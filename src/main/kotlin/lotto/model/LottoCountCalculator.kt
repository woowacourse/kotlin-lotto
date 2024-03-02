package lotto.model

class LottoCountCalculator(
    private val purchasePrice: Int,
    private val manualLottoNumbers: List<List<Int>>,
) {
    fun calculate(): Int {
        return purchasePrice / PRICE_OF_LOTTO_TICKET - manualLottoNumbers.size
    }

    companion object {
        const val PRICE_OF_LOTTO_TICKET = 1000
    }
}
