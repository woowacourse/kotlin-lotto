package lotto.model

class Lotto(
    val numbers: Set<LottoNumber>,
) {
    init {
        require(numbers.size == LOTTO_NUMBERS_SIZE) { "로또 번호는 ${LOTTO_NUMBERS_SIZE}개여야 합니다. 현재 입력된 갯수는 ${numbers.size}개 입니다." }
    }

    fun matchCount(other: Lotto): Int = numbers.count { other.contains(it) }

    fun contains(number: LottoNumber): Boolean = numbers.contains(number)

    companion object {
        const val LOTTO_NUMBERS_SIZE: Int = 6
        const val LOTTO_COUNT_MESSAGE = "로또 번호는 6개여야 합니다."
        const val LOTTO_DISTINCT_MESSAGE = "로또 번호가 중복될 수 없습니다."

        fun from(numbers: List<Int>): Lotto? {
            require(numbers.size == LOTTO_NUMBERS_SIZE) { LOTTO_COUNT_MESSAGE }
            require(numbers.distinct().size == LOTTO_NUMBERS_SIZE) { LOTTO_DISTINCT_MESSAGE }
            val lottoNumbers = numbers.mapNotNull { LottoNumber.from(it) }.toSet()
            return if (lottoNumbers.size == LOTTO_NUMBERS_SIZE) Lotto(lottoNumbers) else null
        }
    }
}
