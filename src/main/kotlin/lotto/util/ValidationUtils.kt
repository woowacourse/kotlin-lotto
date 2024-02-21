package lotto.util

import lotto.model.Lotto
import lotto.model.LottoMachine.TICKET_PRICE

object ValidationUtils {
    fun validatePurchaseAmount(input: String) {
        requireNotNull(input.toIntOrNull()) { "구입 금액은 숫자로 입력해주세요." }
        require(input.toInt() % TICKET_PRICE == 0) {"구입 단위는 ${TICKET_PRICE}원 단위 입니다."}
    }

    fun validateWinningNumbers(input: String) {
        val beforeValidate: List<String> = input.split(",").map { it.trim() }
        beforeValidate.forEach {
            requireNotNull(it.toIntOrNull()){ "당첨 번호는 숫자로 입력해주세요." }
            require(it.toInt() in Lotto.NUMBER_RANGE){ "당첨 번호의 범위는 ${Lotto.NUMBER_RANGE.first}~${Lotto.NUMBER_RANGE.last} 사이의 숫자입니다." }
        }
        require(beforeValidate.toSet().size == Lotto.NUMBER_COUNT) { "당첨 번호는 중복되지 않은 6개의 숫자여야 합니다." }
    }
}
