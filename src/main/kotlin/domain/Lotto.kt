package domain

class Lotto(private val numbers: Set<LottoNumber>) {
    constructor(vararg numbers: Int) : this(numbers.map(::LottoNumber).toSet())

    init {
        require(numbers.size == NUMBER_SIZE) { ERROR_NUMBER_SIZE.format(numbers.size) }
    }

    fun get(): Set<LottoNumber> = numbers

    fun contains(lottoNumber: LottoNumber): Boolean = numbers.contains(lottoNumber)

    fun count(predict: (LottoNumber) -> Boolean): Int = numbers.count(predict)

    fun toList() = numbers.toList()
    override fun toString(): String = numbers.joinToString("")

    companion object {
        const val NUMBER_SIZE = 6
        private const val ERROR_NUMBER_SIZE = "로또 번호의 개수는 6개여야 합니다. \n 잘못된 값 : %d"
    }
}
