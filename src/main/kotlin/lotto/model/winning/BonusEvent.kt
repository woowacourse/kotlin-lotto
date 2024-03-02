package lotto.model.winning

import lotto.model.user.UserException
import lotto.model.user.UserException.BonusException
import lotto.model.LottoEvent

sealed interface BonusEvent {
    data class Success(val bonusNumber: BonusNumber) : BonusEvent
    data object InvalidDataType : BonusEvent
    data object InvalidNumRange : BonusEvent
    data object InvalidBonusDuplication : BonusEvent
    data object UnknownError : BonusEvent

    companion object {
        fun checkBonusEvent(event: BonusEvent): BonusException {
            return when (event) {
                is Success -> BonusException(UserException.SUCCESS, event)
                is InvalidDataType -> BonusException(UserException.INVALID_DATA_TYPE, event)
                is InvalidNumRange -> BonusException(LottoEvent.INVALID_NUM_RANGE, event)
                is InvalidBonusDuplication -> BonusException(INVALID_BONUS_DUPLICATION, event)
                is UnknownError -> BonusException(UserException.UNKNOWN_ERROR, event)
            }
        }

        const val INVALID_BONUS_DUPLICATION = "로또 번호와 중복 된 숫자가 없어야 합니다."
    }
}
