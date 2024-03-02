package exception

sealed class Exceptions(reason: String) : Exception(reason) {
    class PurchaseAmountRangeException(reason: String) : Exceptions(reason)

    class PurchaseAmountUnitException(reason: String) : Exceptions(reason)

    class PurchaseSizeOfManualLottoException(reason: String) : Exceptions(reason)

    class PurchaseSizeOfManualLottoAmountException(reason: String) : Exceptions(reason)

    class LottoNumberException(reason: String) : Exceptions(reason)

    class LottoNumberRangeException(reason: String) : Exceptions(reason)

    class WinningNumberSizeException(reason: String) : Exceptions(reason)

    class WinningNumberDuplication(reason: String) : Exceptions(reason)

    class BonusNumberDuplicationWithWinningNumber(reason: String) : Exceptions(reason)
}
