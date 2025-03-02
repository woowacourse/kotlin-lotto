package domain.model.number

class LottoNumber private constructor(val value: Int) {
    init {
        check(value in LOTTO_MIN..LOTTO_MAX) {
            throw LottoNumberException.InvalidLottoNumberRange()
        }
    }

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45

        private val NUMBERS: MutableMap<Int, LottoNumber> = mutableMapOf()

        fun from(value: Int): LottoNumber {
            return NUMBERS.getOrPut(value) { LottoNumber(value) }
        }
    }
}
