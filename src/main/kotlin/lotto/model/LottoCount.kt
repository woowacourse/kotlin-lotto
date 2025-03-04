package lotto.model

class LottoCount(
    val count: Int,
) {
    init {
        require(count >= MIN_LOTTO_COUNT) { ERROR_MIN_LOTTO_COUNT.format(count) }
    }

    fun isAvailablePurchase(lottoCount: LottoCount): Boolean = count >= lottoCount.count

    fun subtract(lottoCount: LottoCount): LottoCount = LottoCount(count - lottoCount.count)

    companion object {
        private const val MIN_LOTTO_COUNT = 0
        private const val ERROR_MIN_LOTTO_COUNT = "로또 개수 %d는 $MIN_LOTTO_COUNT 이상이 아닙니다."
    }
}
