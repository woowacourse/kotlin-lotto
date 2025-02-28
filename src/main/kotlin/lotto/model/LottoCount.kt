package lotto.model

class LottoCount(
    val count: Int,
) {
    fun subtract(lottoCount: LottoCount): LottoCount {
        require(count >= lottoCount.count) { ERROR_LOTTO_COUNT.format(lottoCount.count, count) }
        return LottoCount(count - lottoCount.count)
    }

    companion object {
        private const val ERROR_LOTTO_COUNT = "빼려는 값 %d이 %d 보다 클 수 없습니다."
    }
}
