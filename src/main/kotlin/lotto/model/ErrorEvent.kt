package lotto.model

sealed interface ErrorEvent {

    sealed class PurchaseEvent(val message: String) : ErrorEvent {
        data object InvalidDataType : PurchaseEvent(INVALID_DATA_TYPE)
        data object InvalidPrice : PurchaseEvent(INVALID_PRICE)
    }

    sealed class LottoEvent(val message: String) : ErrorEvent {
        data object InvalidDataType : LottoEvent(INVALID_DATA_TYPE)
        data object InvalidNumRange : LottoEvent(INVALID_NUM_RANGE)
        data object InvalidNumCount : LottoEvent(INVALID_NUM_COUNT)
        data object InvalidDuplication : LottoEvent(INVALID_DUPLICATION)
    }

    sealed class ManualEvent(val message: String) : ErrorEvent {
        data object InvalidManualCount : ManualEvent(INVALID_MANUAL_COUNT)
    }

    sealed class BonusEvent(val message: String) : ErrorEvent {
        data object InvalidBonusDuplication : LottoEvent(INVALID_BONUS_DUPLICATION)
    }

    companion object {
        private const val INVALID_DATA_TYPE = "숫자 형식을 입력해야 합니다."
        private const val INVALID_PRICE = "구입 금액이 올바르지 않습니다."
        private val INVALID_NUM_RANGE =
            "${Lotto.LOTTO_NUM_RANGE.first}~${Lotto.LOTTO_NUM_RANGE.last} 사이의 숫자를 입력해야 합니다."
        private const val INVALID_NUM_COUNT = "총 ${Lotto.LOTTO_LEN}개의 숫자가 입력되어야 합니다."
        private const val INVALID_DUPLICATION = "중복 된 숫자가 없어야 합니다."
        private const val INVALID_MANUAL_COUNT = "구입 금액에 맞는 수를 입력해야 합니다."
        private const val INVALID_BONUS_DUPLICATION = "로또 번호와 중복 된 숫자가 없어야 합니다."
    }
}
