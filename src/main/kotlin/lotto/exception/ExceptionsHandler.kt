package lotto.exception

object ExceptionsHandler {
    fun handleValidation(
        errorCode: ErrorCode,
        value: () -> Boolean,
    ) {
        if (!value()) {
            val errorException = createErrorException(errorCode.status, errorCode.reason)
            errorException?.let { e -> throw e }
        }
    }

    private fun createErrorException(
        status: Int,
        reason: String,
    ): Throwable? =
        when (status) {
            1000 -> Exceptions.DuplicateNumbersException(reason = reason)
            1001 -> Exceptions.BonusNumberDuplicateException(reason = reason)
            1002 -> Exceptions.PurchaseAmountNotNaturalNumberException(reason = reason)
            1003 -> Exceptions.InvalidPurchaseAmountException(reason = reason)
            1004 -> Exceptions.LottoNumberOutOfRangeException(reason = reason)
            1005 -> Exceptions.InvalidNumberException(reason = reason)
            else -> null
        }
}
