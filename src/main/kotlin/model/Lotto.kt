package model

data class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_NUM_SIZE) { EXCEPTION_LOTTO_NUM_SIZE }
        require(numbers.distinct().size == LOTTO_NUM_SIZE) { EXCEPTION_DUPLICATED_LOTTO_NUM }
        require(numbers.sortedBy { it.number } == numbers) { EXCEPTION_SORTED_LOTTO_NUMS }
    }

    constructor(vararg numbers: Int) : this(numbers.map { LottoNumber(it) })

    fun getMatchCount(other: Lotto): Int = (other.numbers intersect numbers.toSet()).size

    operator fun contains(number: LottoNumber): Boolean = number in numbers

    companion object {
        private const val LOTTO_NUM_SIZE = 6
        private const val EXCEPTION_LOTTO_NUM_SIZE = "로또 숫자의 개수는 ${LOTTO_NUM_SIZE}만 가능합니다."
        private const val EXCEPTION_DUPLICATED_LOTTO_NUM = "로또 숫자는 중복되어서는 안됩니다."
        private const val EXCEPTION_SORTED_LOTTO_NUMS = "로또 숫자는 오름차순으로 정렬되어야합니다."
    }
}
