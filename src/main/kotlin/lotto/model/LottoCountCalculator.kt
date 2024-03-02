package lotto.model

class LottoCountCalculator {
    fun calculate(
        purchasePrice: Int,
        manualLottoNumbers: List<List<Int>>,
    ): Int {
        return purchasePrice / PRICE_OF_LOTTO_TICKET - manualLottoNumbers.size
    }

    companion object {
        const val PRICE_OF_LOTTO_TICKET = 1000
    }
}
