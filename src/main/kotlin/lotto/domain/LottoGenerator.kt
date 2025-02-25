package lotto.domain

class LottoGenerator(
    private val manualQuantity: Int,
    private val automaticQuantity: Int,
) {
    init {
        require(manualQuantity >= 0) { ERROR_MESSAGE_NOT_ENOUGH_MANUAL_LOTTOS }
        require(automaticQuantity >= 0) { ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTOS }
    }

    companion object {
        private const val ERROR_MESSAGE_NOT_ENOUGH_MANUAL_LOTTOS = "수동으로 구매할 로또의 개수는 0보다 작을 수 없습니다."
        private const val ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTOS = "수동으로 구매할 로또의 개수는 로또의 총 개수를 초과할 수 없습니다."
    }
}
