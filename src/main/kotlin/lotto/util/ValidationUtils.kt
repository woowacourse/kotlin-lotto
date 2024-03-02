package lotto.util

object ValidationUtils {
    fun validatePurchaseAmount(input: String) {
        requireNotNull(input.toIntOrNull()) { "구입 금액은 숫자로 입력해주세요." }
    }

    fun validateManualCounts(input: String) {
        requireNotNull(input.toIntOrNull()) { "수동으로 발행할 개수를 숫자로 입력해 주세요." }
    }

    fun validateIfInteger(input: String) {
        requireNotNull(input.toIntOrNull()) { "로또 번호는 숫자로 입력해 주세요." }
    }
}
