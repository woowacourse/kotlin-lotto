package lotto.util

import lotto.model.ErrorEvent
import lotto.model.Lotto

object ErrorMessage {

    fun getErrorMessage(event: ErrorEvent): String {
        return when (event) {
            is ErrorEvent.PurchaseEvent -> getPurchaseMessage(event)
            is ErrorEvent.LottoEvent -> getLottoMessage(event)
            is ErrorEvent.ManualEvent -> getManualMessage(event)
            is ErrorEvent.BonusEvent -> getBonusMessage(event)
        }
    }

    private fun getPurchaseMessage(event: ErrorEvent.PurchaseEvent): String {
        return when (event) {
            is ErrorEvent.PurchaseEvent.InvalidDataType -> INVALID_DATA_TYPE
            is ErrorEvent.PurchaseEvent.InvalidPrice -> INVALID_PRICE
        }
    }

    private fun getLottoMessage(event: ErrorEvent.LottoEvent): String {
        return when (event) {
            is ErrorEvent.LottoEvent.InvalidDataType -> INVALID_DATA_TYPE
            is ErrorEvent.LottoEvent.InvalidNumRange -> INVALID_NUM_RANGE
            is ErrorEvent.LottoEvent.InvalidNumCount -> INVALID_NUM_COUNT
            is ErrorEvent.LottoEvent.InvalidDuplication -> INVALID_DUPLICATION
        }
    }

    private fun getManualMessage(event: ErrorEvent.ManualEvent): String {
        return when (event) {
            is ErrorEvent.ManualEvent.InvalidManualCount -> INVALID_MANUAL_COUNT
        }
    }

    private fun getBonusMessage(event: ErrorEvent.BonusEvent): String {
        return when (event) {
            is ErrorEvent.BonusEvent.InvalidDataType -> INVALID_DATA_TYPE
            is ErrorEvent.BonusEvent.InvalidNumRange -> INVALID_NUM_RANGE
            is ErrorEvent.BonusEvent.InvalidBonusDuplication -> INVALID_BONUS_DUPLICATION
        }
    }

    const val INVALID_DATA_TYPE = "숫자 형식을 입력해야 합니다."
    const val INVALID_PRICE = "구입 금액이 올바르지 않습니다."
    val INVALID_NUM_RANGE =
        "${Lotto.LOTTO_NUM_RANGE.first}~${Lotto.LOTTO_NUM_RANGE.last} 사이의 숫자를 입력해야 합니다."
    const val INVALID_NUM_COUNT = "총 ${Lotto.LOTTO_LEN}개의 숫자가 입력되어야 합니다."
    const val INVALID_DUPLICATION = "중복 된 숫자가 없어야 합니다."
    const val INVALID_MANUAL_COUNT = "구입 금액에 맞는 수를 입력해야 합니다."
    const val INVALID_BONUS_DUPLICATION = "로또 번호와 중복 된 숫자가 없어야 합니다."
}
