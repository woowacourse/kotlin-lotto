package lotto.exception

sealed class Exceptions : Throwable() {
    class DuplicateNumbersException(val reason: String) : Exceptions()

    class BonusNumberDuplicateException(val reason: String) : Exceptions()

    class PurchaseAmountNotNaturalNumberException(val reason: String) : Exceptions()

    class InvalidPurchaseAmountException(val reason: String) : Exceptions()

    class LottoNumberOutOfRangeException(val reason: String) : Exceptions()

    class InvalidNumberException(val reason: String) : Exceptions()

    class ManualPurchaseCountNotNaturalNumberException(val reason: String) : Exceptions()

    class ManualPurchaseCountTooLargeException(val reason: String) : Exceptions()

    class InsufficientDepositException(val reason: String) : Exceptions()
}
