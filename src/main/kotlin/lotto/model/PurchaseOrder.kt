package lotto.model

class PurchaseOrder(
    val price: Int,
    val manualLottoSize: Int = 0,
) {
    val automaticLottoSize: Int

    init {
        require(price.divideByLottoPrice() && price.isMoreThanMin()) { INVALID_DIVIDE_LOTTO_PRICE }
        val totalLottoSize = price / Lotto.PRICE
        require(manualLottoSize.isPurchaseManual(totalLottoSize)) { INVALID_MANUAL_LOTTO_SIZE.format(totalLottoSize) }
        automaticLottoSize = totalLottoSize - manualLottoSize
    }

    private fun Int.divideByLottoPrice() = this % Lotto.PRICE == 0

    private fun Int.isMoreThanMin() = this >= Lotto.PRICE

    private fun Int.isPurchaseManual(totalLottoSize: Int) = this in MIN_MANUAL_SIZE..totalLottoSize

    companion object {
        private const val MIN_MANUAL_SIZE = 0
        private const val INVALID_DIVIDE_LOTTO_PRICE = "${Lotto.PRICE}원 단위로 입력해 주세요."
        private const val INVALID_MANUAL_LOTTO_SIZE = "${MIN_MANUAL_SIZE}에서 %s까지의 수를 입력해 주세요."
    }
}
