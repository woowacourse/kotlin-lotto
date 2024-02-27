package lotto.model

import java.util.TreeSet

class LottoNumber(private val numbers: TreeSet<Int>) {
    init {
        if (numbers.size != Lotto.LOTTO_LEN)
            throw ExceptionManager.getException(UserEvent.LottoEvent.InvalidNumCount)
        if (!numbers.all { it in Lotto.LOTTO_NUM_RANGE })
            throw ExceptionManager.getException(UserEvent.LottoEvent.InvalidNumRange)

    }

    fun getNumbers(): TreeSet<Int> {
        return numbers
    }
}
