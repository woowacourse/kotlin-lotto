package lotto.model

import lotto.model.user.UserEvent

class LottoNumber(
    private val number: Int
) {
    init {
        if (number !in Lotto.LOTTO_NUM_RANGE)
            throw ExceptionManager.getException(UserEvent.LottoEvent.InvalidNumRange)
    }

    fun getNumber(): Int{
        return number
    }
}
