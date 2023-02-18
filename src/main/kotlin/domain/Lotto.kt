package domain

class Lotto private constructor(private val numbers: List<LottoNumber>) {

    init {
        require(numbers.size == NUMBER_SIZE) { ERROR_NUMBER_SIZE.format(numbers.size) }
        require(numbers.isNotDuplicated()) { ERROR_NUMBER_DUPLICATED.format(numbers) }
        require(numbers.sortedBy { it.number } == numbers) { ERROR_NUMBER_SEQUENCE }
    }

    private fun List<LottoNumber>.isNotDuplicated(): Boolean = this.distinct().size == this.size
    fun contains(lottoNumber: LottoNumber): Boolean = numbers.contains(lottoNumber)
    fun count(predicate: () -> Boolean): Int = numbers.count { predicate() }
    fun toList(): List<LottoNumber> = numbers.toList()
    override fun equals(other: Any?): Boolean = if (other is Lotto) other.toList() == this.toList() else false

    override fun hashCode(): Int = this.toList().hashCode()

    override fun toString(): String = this.toList().toString()

    companion object {
        private const val NUMBER_SIZE = 6
        private const val ERROR_NUMBER_SIZE = "로또 번호의 개수는 ${NUMBER_SIZE}개여야 합니다. \n 잘못된 값 : %d"
        private const val ERROR_NUMBER_DUPLICATED = "로또 번호는 중복되어선 안된다. \n잘못된 값: %s"
        private const val ERROR_NUMBER_SEQUENCE = "로또 번호는 정렬되어야 합니다."
        private val NUMBERS = (0..45).toList()
        fun create(numbers: List<Int>): Lotto = Lotto(numbers.map { LottoNumber(NUMBERS[it]) })
    }
}
