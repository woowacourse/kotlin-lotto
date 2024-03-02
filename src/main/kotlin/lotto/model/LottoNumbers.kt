package lotto.model

import lotto.model.user.UserEvent

class LottoNumbers(private val numbers: Set<LottoNumber>) {
    init {
        if (numbers.size != Lotto.LOTTO_LEN)
            throw ExceptionManager.getException(UserEvent.LottoEvent.InvalidNumCount)
    }

    fun getNumbers(): Set<Int> {
        return numbers.map { it.getNumber() }.toSet()
    }
}
