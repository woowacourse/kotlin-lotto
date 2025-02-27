package lotto.model

class Lotto private constructor(private val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == NUMBER_COUNT) {
            COUNT_ERROR_MESSAGE
        }

        require(numbers.distinct().size == numbers.size) {
            DUPLICATE_ERROR_MESSAGE
        }
    }

    fun countMatchingNumber(lotto: Lotto): Int = numbers.count { it in lotto.numbers }

    fun isContain(lottoNumber: LottoNumber): Boolean = lottoNumber in numbers

    override fun toString(): String = numbers.toString()

    companion object {
        const val NUMBER_COUNT = 6
        private const val COUNT_ERROR_MESSAGE = "[ERROR] 로또 번호는 6개여야 합니다."
        private const val DUPLICATE_ERROR_MESSAGE = "[ERROR] 로또 번호는 중복될 수 없습니다."

        fun from(input: List<Int>): Lotto {
            return Lotto(input.map(::LottoNumber))
        }
    }
}
