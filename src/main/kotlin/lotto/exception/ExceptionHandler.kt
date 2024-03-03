package lotto.exception

object ExceptionHandler {
    fun handleException(
        error: ErrorCode,
        value: () -> Boolean,
    ) {
        if (!value()) {
            val errorException = createErrorException(error.status, error.message)
            errorException?.let { e -> throw e }
        }
    }

    private fun createErrorException(
        status: Int,
        reason: String,
    ): Throwable? =
        when (status) {
            1000 -> Exceptions.PurchaseAmountRangeException(reason)
            1001 -> Exceptions.PurchaseAmountUnitException(reason)
            1002 -> Exceptions.PurchaseSizeOfManualLottoException(reason)
            1003 -> Exceptions.PurchaseSizeOfManualLottoAmountException(reason)
            1004 -> Exceptions.LottoNumberException(reason)
            1005 -> Exceptions.LottoNumberRangeException(reason)
            1006 -> Exceptions.WinningNumberSizeException(reason)
            1007 -> Exceptions.WinningNumberDuplication(reason)
            1008 -> Exceptions.BonusNumberDuplicationWithWinningNumber(reason)
            else -> null
        }
}
