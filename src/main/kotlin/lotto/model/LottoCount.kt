package lotto.model

class LottoCount(val purchasePrice: Int, val lottoPrice: Int, val manualCount: Int) {
    val autoCount: Int

    init {
        require(manualCount >= MINIMUM_COUNT && lottoPrice > MINIMUM_PRICE) {
            "음수를 입력할 수 없습니다."
        }
        require(
            purchasePrice > MINIMUM_PRICE && purchasePrice % lottoPrice == 0,
        ) {
            "$lottoPrice 단위로 구매할 수 있습니다."
        }

        autoCount = purchasePrice / lottoPrice - manualCount

        require(autoCount >= MINIMUM_COUNT) {
            "구입 금액보다 더 많이 수동 로또를 구입할 수 없습니다"
        }
    }

    companion object {
        private const val MINIMUM_PRICE = 0
        private const val MINIMUM_COUNT = 0
    }
}
