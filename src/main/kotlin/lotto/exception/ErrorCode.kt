package lotto.exception

enum class ErrorCode(val status: Int, val reason: String) {
    DUPLICATE_NUMBER(1000, "로또의 숫자들은 중복되지 않아야 합니다."),
    BONUS_NUMBER_DUPLICATE(1001, "보너스 숫자는 당첨 번호와 중복되면 안됩니다."),
    PURCHASE_AMOUNT_NOT_NATURAL_NUMBER(1002, "구입 금액은 자연수여야 합니다."),
    INVALID_PURCHASE_AMOUNT(1003, "구입 금액은 자연수이면서 1000 이상이여야 합니다."),
    LOTTO_NUMBER_OUT_OF_RANGE(1004, "로또의 숫자들은 1부터 45까지의 숫자로 구성되어야 합니다."),
    INVALID_NUMBER_EXCEPTION(1005, "로또는 숫자여야만 합니다."),
    MANUAL_PURCHASE_COUNT_NOT_NATURAL_NUMBER(1006, "구입 개수는 숫자여야 합니다."),
}
