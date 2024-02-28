package lotto.util

object ValidationUtils {
    fun validatePurchaseAmount(input: String) {
        requireNotNull(input.toIntOrNull()) { "구입 금액은 숫자로 입력해주세요." }
    }

    fun validateLottoNumber(input: String) {
        requireNotNull(input.toIntOrNull()) { "로또 번호는 숫자로 입력해주세요." }
    }
}
