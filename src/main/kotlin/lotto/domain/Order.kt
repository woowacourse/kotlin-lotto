package lotto.domain

class Order(
    private val purchaseAmount: Int,
) {
    init {
        require(purchaseAmount >= LOTTO_PRICE) { ERROR_UNDER_THOUSAND }
    }

    private val totalCount = getTotalCount()
    var manualCount: Int = 0
        private set

    fun getAutoCount(): Int {
        return totalCount - manualCount
    }

    fun setManualCount(count: Int) {
        require(count <= totalCount) { ERROR_OVER_MAX_COUNT }
        manualCount = count
    }

    private fun getTotalCount(): Int {
        return purchaseAmount / 1_000
    }

    companion object {
        const val LOTTO_PRICE: Int = 1_000
        const val ERROR_UNDER_THOUSAND = "[ERROR] 최소 로또 구입 금액은 1,000원입니다."
        const val ERROR_OVER_MAX_COUNT = "[ERROR] 수동 구매 개수는 총 구매 가능 개수를 초과할 수 없습니다."
    }
}
