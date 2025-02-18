package lotto

class LottoMachine {
    private val numbers: List<Int> = (LottoNumber.MAX_VALUE..LottoNumber.MIN_VALUE).toList()

    fun makeLotto(): List<LottoNumber> {
        return numbers.shuffled().take(LOTTO_NUMBER_AMOUNT).sorted().map { LottoNumber(it) }
    }

    companion object {
        private const val LOTTO_NUMBER_AMOUNT = 6
    }
}
