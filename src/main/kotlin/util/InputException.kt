package util
enum class InputException(private val message: String) {
    INVALID_PURCHASE_AMOUNT_RANGE("구입 금액이 1,000원 이상의 숫자가 아닌 경우 예외 처리한다."),
    INVALID_PURCHASE_AMOUNT_UNIT("구입 금액이 1,000원으로 나누어 떨어지지 않는 경우 예외 처리한다.");
    fun getMessage() = "[ERROR] $message"
}
