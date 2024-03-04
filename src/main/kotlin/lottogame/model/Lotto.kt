package lottogame.model

data class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_NUM_SIZE) { EXCEPTION_LOTTO_NUM_SIZE.format(numbers) }
        require(numbers.distinct().size == LOTTO_NUM_SIZE) { EXCEPTION_DUPLICATED_LOTTO_NUM.format(numbers) }
        require(numbers.sortedBy { it.number } == numbers) { EXCEPTION_SORTED_LOTTO_NUMS.format(numbers) }
    }

    constructor(vararg numbers: Int) : this(numbers.map { GeneralLottoNumber(it) })

    fun getMatchCount(other: Lotto): Int = (other.numbers intersect numbers).size

    operator fun contains(number: LottoNumber): Boolean = number in numbers

    companion object {
        private const val LOTTO_NUM_SIZE = 6
        private const val EXCEPTION_LOTTO_NUM_SIZE = "%s : 로또 숫자의 개수는 ${LOTTO_NUM_SIZE}만 가능합니다."
        private const val EXCEPTION_DUPLICATED_LOTTO_NUM = "%s : 로또 숫자는 중복되어서는 안됩니다."
        private const val EXCEPTION_SORTED_LOTTO_NUMS = "%s : 로또 숫자는 오름차순으로 정렬되어야합니다."

        @JvmStatic
        fun createLottoResult(lottoNumbers: List<LottoNumber>): LottoResult {
            if (lottoNumbers.size != LOTTO_NUM_SIZE) {
                return LottoResult.InvalidNumberSize(
                    EXCEPTION_LOTTO_NUM_SIZE.format(lottoNumbers),
                )
            }
            if (lottoNumbers.distinct().size != LOTTO_NUM_SIZE) {
                return LottoResult.InvalidDuplicateNumber(
                    EXCEPTION_DUPLICATED_LOTTO_NUM.format(lottoNumbers),
                )
            }
            if (lottoNumbers.sortedBy { it.number } != lottoNumbers.toList()) {
                return LottoResult.InvalidSort(
                    EXCEPTION_SORTED_LOTTO_NUMS.format(lottoNumbers),
                )
            }
            return LottoResult.Success(Lotto(lottoNumbers))
        }
    }
}

sealed interface LottoResult {
    data class Success(val lotto: Lotto) : LottoResult

    data class InvalidNumberSize(val message: String) : LottoResult

    data class InvalidDuplicateNumber(val message: String) : LottoResult

    data class InvalidSort(val message: String) : LottoResult
}
