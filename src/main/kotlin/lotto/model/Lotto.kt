package lotto.model

data class Lotto(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == NUMBER_COUNT) { "로또 번호는 중복되지 않는 6개의 수로 구성되어야 합니다." }
    }

    constructor(vararg numbers: Int) : this(numbers.map { LottoNumber.valueOf(it) }.filterNotNull().toSet()) {}

    fun countMatchingNumbers(winningNumbers: Lotto): Int = numbers.intersect(winningNumbers.numbers).size

    fun hasMatchingBonusNumbers(bonusNumber: LottoNumber): Boolean = numbers.contains(bonusNumber)

    override fun toString(): String = numbers.toString()

    companion object {
        const val NUMBER_COUNT = 6
    }
}
