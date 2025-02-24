package lotto.domain

class ManualLottoCount(val lottoCount: Int, purchasableCount: Int) {
    init {
        require(lottoCount >= 0) { ERROR_MANUAL_LOTTO_COUNT_MUST_BE_NON_NEGATIVE }
        require(lottoCount <= purchasableCount) { ERROR_MANUAL_LOTTO_COUNT_EXCEEDS_PURCHASABLE }
    }

    companion object {
        private const val ERROR_MANUAL_LOTTO_COUNT_MUST_BE_NON_NEGATIVE = "[ERROR] 수동 로또 개수는 0이상 이어야 합니다."
        private const val ERROR_MANUAL_LOTTO_COUNT_EXCEEDS_PURCHASABLE = "[ERROR] 구입 가능한 로또의 개수보다 많을 수 없습니다."
    }
}
