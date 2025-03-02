package lotto.domain.model

sealed class PurchaseAmountResult {
    fun getSuccessOrThrow(): PurchaseAmount {
        require(this is Success) { "${this::class.simpleName} 문제가 발생 했습니다." }
        return this.purchaseAmount
    }

    data class Success(val purchaseAmount: PurchaseAmount) : PurchaseAmountResult()

    data class InvalidAmount(val amount: Int) : PurchaseAmountResult()

    data object InvalidAmountNull : PurchaseAmountResult()
}

class PurchaseAmount private constructor(private var _amount: Int) {
    private val amount get() = _amount

    fun getPurchaseLottoCount(
        purchaseCount: Int,
        lottoPrice: Int = Lotto.LOTTO_PRICE,
    ): Int {
        val purchaseAmount = lottoPrice * purchaseCount
        require(purchaseAmount <= amount) { INVALID_PURCHASE_LOTTO_AMOUNT_MESSAGE.format(purchaseAmount, amount) }
        _amount -= purchaseAmount
        return purchaseCount
    }

    fun getPurchaseRemainLottoCount(lottoPrice: Int = Lotto.LOTTO_PRICE): Int {
        val remainLottoCount = amount / lottoPrice
        _amount -= lottoPrice * remainLottoCount
        return remainLottoCount
    }

    companion object {
        fun from(amount: Int?): PurchaseAmountResult {
            amount ?: return PurchaseAmountResult.InvalidAmountNull
            if (amount < Lotto.LOTTO_PRICE) return PurchaseAmountResult.InvalidAmount(amount)
            return PurchaseAmountResult.Success(PurchaseAmount(amount))
        }

        private const val INVALID_PURCHASE_LOTTO_AMOUNT_MESSAGE =
            "선택하신 개수의 금액은 %d 입니다. 구매하시는 금액은 현재 구매할 %d원 보다 적어야 합니다."
    }
}
