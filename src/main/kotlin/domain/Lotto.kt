package domain

data class Lotto(private val numbers: Set<LottoNumber>) : Set<LottoNumber> by numbers {

    init {
        require(numbers.size == NUMBER_SIZE) { ERROR_NUMBER_SIZE.format(numbers.size) }
    }

    companion object {
        const val NUMBER_SIZE = 6
        private const val ERROR_NUMBER_SIZE = "로또 번호의 개수는 6개여야 합니다. \n 잘못된 값 : %d"
        fun create(numbers: Set<Int>): Lotto = Lotto(numbers.map { LottoNumber(it) }.toSet())
    }
}
