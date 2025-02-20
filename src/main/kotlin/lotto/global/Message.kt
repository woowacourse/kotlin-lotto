package lotto.global

enum class Message(val msg:String) {
    ASK_AMOUNT("구입금액을 입력해 주세요."),
    ASK_WINNING_LOTTO("지난 주 당첨 번호를 입력해 주세요."),
    ASK_BONUS_BALL("보너스 볼을 입력해 주세요."),

    ERR_INVALID_FORMAT("올바르지 않은 형식입니다"),
    ERR_NOT_SIX_ELEMENTS("6개의 숫자를 입력해주세요"),
    ERR_NOT_IN_RANGE("1부터 45까지의 숫자를 입력해주세요"),
    ERR_ELEMENT_DUPLICATED("중복되지 않은 숫자를 입력해주세요")
}