package lotto.model.manual

import lotto.model.user.UserException
import lotto.model.user.UserException.ManualException

sealed interface ManualEvent {
    data class Success(val manualNumber: ManualNumber) : ManualEvent
    data object InvalidDataType : ManualEvent
    data object InvalidManualCount : ManualEvent
    data object UnknownError : ManualEvent

    companion object {
        fun checkManual(event: ManualEvent): ManualException {
            return when (event) {
                is Success -> ManualException(UserException.SUCCESS, event)
                is InvalidDataType -> ManualException(UserException.INVALID_DATA_TYPE, event)
                is InvalidManualCount -> ManualException(INVALID_MANUAL_COUNT, event)
                is UnknownError -> ManualException(UserException.UNKNOWN_ERROR, event)
            }
        }

        const val INVALID_MANUAL_COUNT = "구입 금액에 맞는 수를 입력해야 합니다."
    }
}
