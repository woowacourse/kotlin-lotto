package domain

class Lotto constructor(private val numbers: Set<LottoNumber>) : Cloneable {
    val size: Int
        get() = numbers.size

    init {
        require(numbers.size == LOTTO_SIZE) { ERROR_LOTTO_SIZE.format(numbers.size) }
    }

    constructor(vararg number: Int) : this(number.map(LottoNumber::from).toSet())

    fun matchNumbers(winningNumbers: Lotto): Int = winningNumbers.numbers.count { numbers.contains(it) }

    fun matchBonusNumber(bonusNumber: LottoNumber): Boolean = numbers.contains(bonusNumber)

    fun forEach(action: (LottoNumber) -> Unit) {
        numbers.forEach(action)
    }

    fun contains(number: LottoNumber): Boolean {
        return numbers.contains(number)
    }

    fun containsAll(lotto: Lotto): Boolean {
        return numbers.containsAll(lotto.numbers)
    }

    public override fun clone(): Lotto {
        return Lotto(numbers.toSet())
    }

    companion object {
        const val LOTTO_SIZE = 6
        private const val ERROR_LOTTO_SIZE = "[ERROR] 현재의 로또 번호 개수는 %d개, 로또 번호는 ${LOTTO_SIZE}개여야 합니다."
        fun create(numbers: Set<Int>) = Lotto(numbers.map(LottoNumber::from).toSet())
    }
}
