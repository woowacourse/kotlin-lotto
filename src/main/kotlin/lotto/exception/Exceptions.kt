package lotto.exception

sealed class Exceptions(reason: String) : Exception(reason) {
    class PurchaseAmountRangeException(reason: String = "구입 금액이 1,000원 이상의 숫자가 아닌 경우 예외 처리한다.") : Exceptions(reason)

    class PurchaseSizeOfManualLottoException(reason: String = "수동 로또 수가 0 이상의 숫자가 아닌 경우 예외 처리한다.") : Exceptions(reason)

    class PurchaseSizeOfManualLottoAmountException(reason: String = "수동 로또 수가 구입 금액보다 많은 경우 예외 처리한다.") :
        Exceptions(reason)

    class WinningLottoSizeException(reason: String = "당첨 로또의 개수가 6개가 아닌 경우 예외 처리한다.") : Exceptions(reason)

    class WinningLottoDuplication(reason: String = "당첨 로또의 숫자가 중복되는 경우 예외 처리한다.") : Exceptions(reason)

    class BonusNumberDuplicationWithWinningNumber(reason: String = "보너스 번호가 당첨 숫자와 중복되는 경우 예외 처리한다.") :
        Exceptions(reason)
}
