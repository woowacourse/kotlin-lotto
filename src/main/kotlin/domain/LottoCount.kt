package domain

class LottoCount(private val count: Int) {
    init {
        require(count >= 0) { ERROR_MANUAL_COUNT }
    }

    companion object {
        private const val ERROR_MANUAL_COUNT = "구매한 로또의 수보다 많습니다."
    }
}
