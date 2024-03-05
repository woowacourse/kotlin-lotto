package lotto.model

@JvmInline
value class LottoNumber private constructor(private val number: Int) {
    override fun toString(): String = number.toString()

    companion object {
        val NUMBER_RANGE = (1..45)
        private val CACHED_LOTTO_NUMBERS = NUMBER_RANGE.associateWith(::LottoNumber)

        fun valueOf(number: Int): LottoNumber {
            return CACHED_LOTTO_NUMBERS[number] ?: throw IllegalArgumentException("로또 번호의 범위는 1~45 사이의 자연수입니다.")
        }
    }
}
