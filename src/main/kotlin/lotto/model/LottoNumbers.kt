package lotto.model

import lotto.model.user.UserEvent
import java.util.TreeSet

class LottoNumbers(private val numbers: List<LottoNumber>) {
    init {
        if (numbers.size != Lotto.LOTTO_LEN)
            throw ExceptionManager.getException(UserEvent.LottoEvent.InvalidNumCount)
    }

    fun getNumbers(): TreeSet<Int> {
        return TreeSet(
            numbers.map { it.getNumber() }
        )
    }
}
