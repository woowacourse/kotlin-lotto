package domain.model

data class Lotto private constructor(
    val numbers: Set<LottoNumber>,
) {
    init {
        require(numbers.size == LOTTO_SIZE) { INVALID_LOTTO_SIZE }
    }

    companion object {
        fun from(inputNumbers: Set<LottoNumber>) = Lotto(inputNumbers)

        fun of(vararg inputNumbers: Int): Lotto = Lotto(inputNumbers.map { LottoNumber.from(it) }.toSet())

        const val ERROR = "[ERROR]"
        const val LOTTO_SIZE = 6
        const val INVALID_LOTTO_SIZE = "$ERROR 로또 번호는 중복되지 않는 6개 입니다."
    }
}
