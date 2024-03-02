package lotto.model

import lotto.model.user.UserException
import lotto.model.user.UserException.LottoException

sealed interface LottoEvent {
    data class Success(val lotto: Lotto): LottoEvent
    data object InvalidDataType : LottoEvent
    data object InvalidNumRange : LottoEvent
    data object InvalidDuplication : LottoEvent
    data object InvalidNumCount : LottoEvent
    data object UnknownError : LottoEvent

    companion object {
        fun checkLotto(event: LottoEvent): LottoException {
            return when(event){
                is Success -> LottoException(UserException.SUCCESS,event)
                is InvalidDataType -> LottoException(UserException.INVALID_DATA_TYPE,event)
                is InvalidNumRange -> LottoException(INVALID_NUM_RANGE,event)
                is InvalidDuplication -> LottoException(INVALID_DUPLICATION,event)
                is InvalidNumCount -> LottoException(INVALID_NUM_COUNT,event)
                is UnknownError -> LottoException(UserException.UNKNOWN_ERROR,event)
            }
        }

        val INVALID_NUM_RANGE = "${Lotto.LOTTO_NUM_RANGE.first}~${Lotto.LOTTO_NUM_RANGE.last} 사이의 숫자를 입력해야 합니다."
        const val INVALID_NUM_COUNT = "총 ${Lotto.LOTTO_LEN}개의 숫자가 입력되어야 합니다."
        const val INVALID_DUPLICATION = "로또 번호에 중복 숫자가 있습니다."
    }
}
