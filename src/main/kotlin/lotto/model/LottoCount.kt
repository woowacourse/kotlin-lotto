package lotto.model

class LottoCount(
    val count: Int,
) {
    init {
        require(count >= MIN_LOTTO_COUNT) { ERROR_MIN_LOTTO_COUNT.format(count) }
    }

    fun validateLottoMaxCount(totalCount: LottoCount) {
        require(count <= totalCount.count) { ERROR_MAX_LOTTO_COUNT.format(totalCount.count, count) }
    }

    fun subtract(lottoCount: LottoCount): LottoCount = LottoCount(count - lottoCount.count)

    companion object {
        private const val MIN_LOTTO_COUNT = 1
        private const val ERROR_MIN_LOTTO_COUNT = "로또 개수 %d는 $MIN_LOTTO_COUNT 이상이 아닙니다."
        private const val ERROR_MAX_LOTTO_COUNT = "발행할 수 있는 로또 개수 %d개보다 %d이(가) 클 수 없습니다."
    }
}
