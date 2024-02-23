package util

enum class InputException(private val message: String) {
    INVALID_PURCHASE_AMOUNT_RANGE("구입 금액이 1,000원 이상의 숫자가 아닌 경우 예외 처리한다."),
    INVALID_PURCHASE_AMOUNT_UNIT("구입 금액이 1,000원으로 나누어 떨어지지 않는 경우 예외 처리한다."),
    INVALID_WINNING_NUMBER_RANGE("당첨 번호가 1~45사이의 숫자가 아닌 경우 예외 처리한다."),
    INVALID_WINNING_NUMBERS_SIZE("당첨 번호의 개수가 6개가 아닌 경우 예외 처리한다."),
    INVALID_WINNING_NUMBERS_DUPLICATE("당첨 숫자가 중복되는 경우 예외 처리한다."),
    INVALID_BONUS_NUMBER_RANGE("보너스 번호가 1~45사이의 숫자가 아닌 경우 예외 처리한다."),
    INVALID_BONUS_NUMBER_DUPLICATE("보너스 번호가 당첨 숫자와 중복되는 경우 예외 처리한다."),
    ;

    fun getMessage() = "[ERROR] $message"
}
