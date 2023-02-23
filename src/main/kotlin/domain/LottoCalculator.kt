package domain

class LottoCalculator(private val price: Int) {
    fun getAutoCount(amount: Amount, manualCount: Count): Count {
        require((amount.divToInt(LottoStore.LOTTO_PRICE)) >= manualCount.toInt()) { ERROR_NUMBER_RANGE.format(manualCount) }
        return Count(manualCount.minusToInt(amount.divToInt(price)))
    }

    companion object {
        private val ERROR_NUMBER_RANGE = "수동 로또 장수가 구입 가능한 총 로또 장수를 초과합니다.\n잘못된 값 : %d"
    }
}
