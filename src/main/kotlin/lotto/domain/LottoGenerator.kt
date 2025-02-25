package lotto.domain

class LottoGenerator(val totalQuantity: Int, val manualQuantity: Int) {
    init {
        require(totalQuantity >= manualQuantity) { ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTOS }
    }

    companion object {
        private const val ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTOS = "수동으로 구매할 로또의 개수는 로또의 총 개수를 초과할 수 없습니다."
    }
}
