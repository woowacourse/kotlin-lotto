package lotto.util

import lotto.model.LottoMachine.TICKET_PRICE

object ValidationUtils {
    fun validatePurchaseAmount(input: String) {
        requireNotNull(input.toIntOrNull()) { "구입 금액은 숫자로 입력해주세요." }
        require(input.toInt() % TICKET_PRICE == 0) { "구입 단위는 ${TICKET_PRICE}원 단위 입니다." }
    }

    fun validateWinningNumbers(input: String) {
        input.split(",").map { it.trim() }.forEach {
            requireNotNull(it.toIntOrNull()) { "당첨 번호는 숫자로 입력해주세요." }
        }
    }

    fun validateBonusNumber(input: String) {
        requireNotNull(input.toIntOrNull()) { "보너스 숫자는 숫자로 입력해주세요." }
    }
}
