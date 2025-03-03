package lotto.domain.model

sealed class PurchaseAmountResult {
    fun getSuccessOrThrow(): PurchaseAmount {
        require(this is Success) { "${this::class.simpleName} 문제가 발생 했습니다." }
        return this.purchaseAmount
    }

    data class Success(val purchaseAmount: PurchaseAmount) : PurchaseAmountResult()

    data class InvalidAmount(val amount: Int) : PurchaseAmountResult()

    data object InvalidAmountNull : PurchaseAmountResult()

    data class InvalidPurchase(val purchaseAmount: Int, val currentAmount: Int) : PurchaseAmountResult()
}

data class PurchaseAmount private constructor(private val amount: Int) {
    fun getRemainPurchaseCount(lottoPrice: Int = Lotto.LOTTO_PRICE) = amount / lottoPrice

    fun purchaseLotto(
        purchaseCount: Int,
        lottoPrice: Int = Lotto.LOTTO_PRICE,
    ): PurchaseAmountResult {
        val purchaseAmount = lottoPrice * purchaseCount
        if (purchaseAmount > amount) return PurchaseAmountResult.InvalidPurchase(purchaseAmount, amount)
        return PurchaseAmountResult.Success(this.copy(amount = amount - purchaseAmount))
    }

    companion object {
        fun from(amount: Int?): PurchaseAmountResult {
            amount ?: return PurchaseAmountResult.InvalidAmountNull
            if (amount < Lotto.LOTTO_PRICE) return PurchaseAmountResult.InvalidAmount(amount)
            return PurchaseAmountResult.Success(PurchaseAmount(amount))
        }
    }
}
