package lotto.domain.model

data class PurchaseCount(val count: Int) {
    init {
        require(count >= 0) { INVALID_MANUAL_COUNT }
    }

    companion object {
        private const val INVALID_MANUAL_COUNT = "수동으로 입력 받을 숫자는 음수가 될 수 없습니다"
    }
}
