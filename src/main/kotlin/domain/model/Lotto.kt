package domain.model

data class Lotto(
    val numbers: Set<LottoNumber>,
) {
    init {
        require(numbers.size == LOTTO_SIZE) { INVALID_LOTTO_SIZE }
    }

    companion object {
        fun of(vararg inputNumbers: Int): Lotto = Lotto(inputNumbers.map { LottoNumber.from(it) }.toSet())

        fun Set<LottoNumber>.toValues() = this.map { it }

        const val ERROR = "[ERROR]"
        const val LOTTO_SIZE = 6
        const val INVALID_LOTTO_SIZE = "$ERROR 로또 번호는 6개 입니다."
    }
}
