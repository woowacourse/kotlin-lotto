package domain.model

@JvmInline
value class PassivityLottoAmount private constructor(val value: Int) {
    companion object {
        private const val INVALID_PASSIVITY_LOTTO_SIZE = "수동 구매 개수가 구입 가능한 로또 개수를 초과했습니다."

        fun create(
            lottoAmount: Int,
            money: PurchasePrice,
        ): PassivityLottoAmount {
            val purchasableAmount = money.getPurchasableLottoCount()
            require(lottoAmount <= purchasableAmount) { INVALID_PASSIVITY_LOTTO_SIZE }
            return PassivityLottoAmount(lottoAmount)
        }
    }
}
