package domain.model

import domain.model.price.PurchasePrice

@JvmInline
value class ManualLottoAmount private constructor(val value: Int) {
    constructor(
        lottoAmount: Int,
        money: PurchasePrice,
    ) : this(lottoAmount) {
        val purchasableAmount = money.getPurchasableLottoCount()
        require(lottoAmount <= purchasableAmount) { INVALID_PASSIVITY_LOTTO_SIZE }
    }

    companion object {
        private const val INVALID_PASSIVITY_LOTTO_SIZE = "수동 구매 개수가 구입 가능한 로또 개수를 초과했습니다."
    }
}
