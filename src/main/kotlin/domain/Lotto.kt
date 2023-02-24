package domain

class Lotto constructor(numbers: Set<LottoNumber>) {
    private val _numbers = numbers.toSet()
    val numbers
        get() = _numbers.toSet()

    init {
        require(numbers.size == LOTTO_SIZE) { ERROR_LOTTO_SIZE.format(numbers.size) }
    }

    constructor(vararg number: Int) : this(number.map(LottoNumber::from).toSet())

    fun matchNumbers(winningNumbers: Lotto): Int = winningNumbers.numbers.count { numbers.contains(it) }

    fun matchBonusNumber(bonusNumber: LottoNumber): Boolean = numbers.contains(bonusNumber)

    companion object {
        const val LOTTO_SIZE = 6
        private const val ERROR_LOTTO_SIZE = "[ERROR] 현재의 로또 번호 개수는 %d개, 로또 번호는 ${LOTTO_SIZE}개여야 합니다."
    }
}
