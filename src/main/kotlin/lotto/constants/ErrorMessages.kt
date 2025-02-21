package lotto.constants

object ErrorMessages {
    private const val ERROR = "[ERROR]"

    const val INVALID_NUMBER_FORMAT = "$ERROR 숫자를 입력해야 합니다."

    const val INVALID_LOTTO_NUMBER_RANGE = "$ERROR 로또 번호는 1에서 45 사이여야 합니다."
    const val INVALID_NUMBER_OF_LOTTO_NUMBERS = "$ERROR 로또 번호는 6개여야 합니다."
    const val DUPLICATE_LOTTO_NUMBER = "$ERROR 로또 번호는 중복되면 안됩니다."

    const val INVALID_PURCHASE_AMOUNT_RANGE = "$ERROR 구입 금액은 1,000원 이상이어야 합니다."
    const val INVALID_PURCHASE_AMOUNT = "$ERROR 구입 금액은 1,000원 단위여야 합니다."

    const val INVALID_EARNING_RATE_RANGE = "$ERROR 수익률은 0 이상이어야 합니다."
}
