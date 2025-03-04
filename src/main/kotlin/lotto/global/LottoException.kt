package lotto.global

enum class LottoException(
    val msg: String,
    var userInput: Any? = "",
) {
    ERR_INVALID_FORMAT("올바르지 않은 형식입니다"),
    ERR_NOT_SIX_ELEMENTS("6개의 숫자를 입력해주세요"),
    ERR_NOT_IN_RANGE("1부터 45까지의 숫자를 입력해주세요"),
    ERR_ELEMENT_DUPLICATED("중복되지 않은 숫자를 입력해주세요"),
    ERR_TOO_MANY_MANUAL_LOTTO("수동으로 구매한 금액이 입력 금액보다 큽니다"),
    ERR_LESS_THAN_MINIMUM_PRICE("최소 구입 금액은 1000원 이상이여야 합니다"),
    ERR_MANUAL_NOT_SUFFICIENT("구매한 수동 로또의 개수만큼 수동 로또를 입력받아야 합니다"),
}
