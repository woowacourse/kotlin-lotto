package lotto.util

object ValidationUtils {
    fun validatePurchaseAmount(input: String) {
        requireNotNull(input.toIntOrNull()) { "구입 금액은 숫자로 입력해주세요." }
    }
}
