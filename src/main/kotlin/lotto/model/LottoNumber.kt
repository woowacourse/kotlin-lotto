package lotto.model

class LottoNumber(
    private val number: Int
) {

    fun getNumber(): Int {
        return number
    }

    companion object {
        fun checkLottoNumberValid(input: String): Int {
            return input.toIntOrNull()
                ?.also {
                    if (it !in Lotto.LOTTO_NUM_RANGE)
                        throw LottoEvent.checkLotto(LottoEvent.InvalidNumRange)
                } ?: throw LottoEvent.checkLotto(LottoEvent.InvalidDataType)
        }
    }
}
