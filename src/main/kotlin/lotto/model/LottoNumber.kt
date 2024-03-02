package lotto.model

class LottoNumber private constructor(private val number: Int) {
    init {
        require(number in NUMBER_RANGE) { "로또 번호의 범위는 1~45 사이의 자연수입니다." }
    }

    override fun toString(): String = number.toString()

    companion object {
        val NUMBER_RANGE = (1..45)
        private val CACHED_LOTTO_NUMBERS = NUMBER_RANGE.associateWith(::LottoNumber)

        fun valueOf(number: Int): LottoNumber? {
            return CACHED_LOTTO_NUMBERS[number]
        }
    }
}
