package lotto.model

class LottoNumber(private val numbers: Set<Int>) {
    init {
        if (numbers.size != Lotto.LOTTO_LEN)
            throw ExceptionManager.getException(UserEvent.LottoEvent.InvalidNumCount)
        if (!numbers.all { it in Lotto.LOTTO_NUM_RANGE })
            throw ExceptionManager.getException(UserEvent.LottoEvent.InvalidNumRange)

    }

    fun getNumbers(): Set<Int> {
        return numbers
    }
}
