package domain.model.manual

sealed class ManualLottoAmountException(override val message: String?) : Throwable(message) {
    class InvalidPurchasableManualLottoSize() : ManualLottoAmountException(
        INVALID_MANUAL_LOTTO_SIZE,
    )

    companion object {
        private const val INVALID_MANUAL_LOTTO_SIZE = "수동 구매 개수가 구입 가능한 로또 개수를 초과했습니다."
    }
}
