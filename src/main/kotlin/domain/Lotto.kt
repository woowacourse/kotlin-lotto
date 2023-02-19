package domain

class Lotto(private val numbers: Set<LottoNumber>) {
    constructor(vararg numbers: Int) : this(numbers.map { LottoNumber.from(it) }.toSet())

    init {
        require(numbers.size == NUMBER_SIZE) { ERROR_NUMBER_SIZE.format(numbers.size) }
    }

    fun contains(lottoNumber: LottoNumber): Boolean = numbers.contains(lottoNumber)

    fun countMatch(otherLotto: Lotto): Int = numbers.intersect(otherLotto.numbers).size

    fun toList() = numbers.toList()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Lotto

        if (numbers != other.numbers) return false

        return true
    }

    override fun hashCode(): Int {
        return numbers.hashCode()
    }

    companion object {
        const val NUMBER_SIZE = 6
        private const val ERROR_NUMBER_SIZE = "로또 번호의 개수는 6개여야 합니다. \n 잘못된 값 : %d"
    }
}
