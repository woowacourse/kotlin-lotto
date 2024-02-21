package model

data class Lotto(private val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_NUM_SIZE) { EXCEPTION_LOTTO_NUM_SIZE }
        require(numbers.distinct().size == LOTTO_NUM_SIZE) { EXCEPTION_DUPLICATED_LOTTO_NUM }
        // TODO sorted 검증문 추가
    }

    constructor(vararg nums: Int) : this(nums.map { LottoNumber(it) })

    fun countMatch(other: Lotto): Int = (other.numbers intersect numbers).size

    operator fun contains(num: LottoNumber): Boolean = num in numbers

    companion object {
        private const val LOTTO_NUM_SIZE = 6
        private const val EXCEPTION_LOTTO_NUM_SIZE = "로또 숫자의 개수는 ${LOTTO_NUM_SIZE}만 가능합니다."
        private const val EXCEPTION_DUPLICATED_LOTTO_NUM = "로또 숫자는 중복되어서는 안됩니다."
    }
}
