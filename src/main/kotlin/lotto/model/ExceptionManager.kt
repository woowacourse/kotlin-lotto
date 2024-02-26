package lotto.model

object ExceptionManager {
    fun getException(event: UserEvent): GameException {
        return when (event) {
            is UserEvent.PurchaseEvent -> getPurchaseMessage(event)
            is UserEvent.LottoEvent -> getLottoMessage(event)
            is UserEvent.ManualEvent -> getManualMessage(event)
            is UserEvent.BonusEvent -> getBonusMessage(event)
        }
    }

    private fun getPurchaseMessage(event: UserEvent.PurchaseEvent): GameException {
        return when (event) {
            is UserEvent.PurchaseEvent.Success -> GameException(SUCCESS, event)
            is UserEvent.PurchaseEvent.InvalidDataType -> GameException(INVALID_DATA_TYPE, event)
            is UserEvent.PurchaseEvent.InvalidPrice -> GameException(INVALID_PRICE, event)
            is UserEvent.PurchaseEvent.UnknownError -> GameException(UNKNOWN_ERROR,event)
        }
    }

    private fun getLottoMessage(event: UserEvent.LottoEvent): GameException {
        return when (event) {
            is UserEvent.LottoEvent.Success -> GameException(SUCCESS, event)
            is UserEvent.LottoEvent.InvalidDataType -> GameException(INVALID_DATA_TYPE, event)
            is UserEvent.LottoEvent.InvalidNumRange -> GameException(INVALID_NUM_RANGE, event)
            is UserEvent.LottoEvent.InvalidNumCount -> GameException(INVALID_NUM_COUNT, event)
            is UserEvent.LottoEvent.InvalidDuplication -> GameException(INVALID_DUPLICATION, event)
            is UserEvent.LottoEvent.UnknownError -> GameException(UNKNOWN_ERROR,event)
        }
    }

    private fun getManualMessage(event: UserEvent.ManualEvent): GameException {
        return when (event) {
            is UserEvent.ManualEvent.Success -> GameException(SUCCESS, event)
            is UserEvent.ManualEvent.InvalidDataType -> GameException(INVALID_DATA_TYPE, event)
            is UserEvent.ManualEvent.InvalidManualCount -> GameException(INVALID_MANUAL_COUNT, event)
            is UserEvent.ManualEvent.UnknownError -> GameException(UNKNOWN_ERROR,event)
        }
    }

    private fun getBonusMessage(event: UserEvent.BonusEvent): GameException {
        return when (event) {
            is UserEvent.BonusEvent.Success -> GameException(SUCCESS, event)
            is UserEvent.BonusEvent.InvalidDataType -> GameException(INVALID_DATA_TYPE, event)
            is UserEvent.BonusEvent.InvalidNumRange -> GameException(INVALID_NUM_RANGE, event)
            is UserEvent.BonusEvent.InvalidBonusDuplication -> GameException(INVALID_BONUS_DUPLICATION, event)
            is UserEvent.BonusEvent.UnknownError -> GameException(UNKNOWN_ERROR,event)
        }
    }

    private const val SUCCESS = ""
    private const val UNKNOWN_ERROR = "알 수 없는 에러가 발생하였습니다."
    const val INVALID_DATA_TYPE = "숫자 형식을 입력해야 합니다."
    const val INVALID_PRICE = "구입 금액이 올바르지 않습니다."
    val INVALID_NUM_RANGE = "${Lotto.LOTTO_NUM_RANGE.first}~${Lotto.LOTTO_NUM_RANGE.last} 사이의 숫자를 입력해야 합니다."
    const val INVALID_NUM_COUNT = "총 ${Lotto.LOTTO_LEN}개의 숫자가 입력되어야 합니다."
    const val INVALID_DUPLICATION = "중복 된 숫자가 없어야 합니다."
    const val INVALID_MANUAL_COUNT = "구입 금액에 맞는 수를 입력해야 합니다."
    const val INVALID_BONUS_DUPLICATION = "로또 번호와 중복 된 숫자가 없어야 합니다."
}
