package lotto.domain.model

sealed class PurchaseCountResult {
    data class Success(val purchaseCount: PurchaseCount) : PurchaseCountResult()

    data object InvalidCountNull : PurchaseCountResult()

    data class InvalidCountRange(val count: Int) : PurchaseCountResult()

    data class PurchaseFail(val purchaseCount: Int, val count: Int) : PurchaseCountResult()
}

data class PurchaseCount private constructor(val count: Int) {
    fun getRemainPurchaseCount(purchaseCount: Int): PurchaseCountResult {
        if (purchaseCount > count) return PurchaseCountResult.PurchaseFail(purchaseCount, count)
        return PurchaseCountResult.Success(this.copy(count = count - purchaseCount))
    }

    companion object {
        fun from(count: Int?): PurchaseCountResult {
            if (count == null) return PurchaseCountResult.InvalidCountNull
            if (count < 0) return PurchaseCountResult.InvalidCountRange(count)
            return PurchaseCountResult.Success(PurchaseCount(count))
        }
    }
}
