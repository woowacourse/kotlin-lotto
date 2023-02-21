package domain

class Lotto constructor(val numbers: Set<LottoNumber>) {

    init {
        require(numbers.size == LOTTO_SIZE) { ERROR_LOTTO_SIZE.format(numbers.size) }
    }

    fun matchResult(winningNumber: WinningLotto): Rank =
        Rank.valueOf(matchNumbers(winningNumber.lotto), matchBonusNumber(winningNumber.bonusNumber))

    private fun matchNumbers(winningNumbers: Lotto): Int = winningNumbers.numbers.count { numbers.contains(it) }

    private fun matchBonusNumber(bonusNumber: LottoNumber): Boolean = numbers.contains(bonusNumber)

    override fun toString(): String {
        val sb = StringBuilder("[")
        numbers.forEach { lottoNumber -> sb.append("$lottoNumber, ") }
        sb.delete(sb.length - 2, sb.length)
        sb.append("]")
        return sb.toString()
    }

    companion object {
        private const val LOTTO_SIZE = 6
        private const val ERROR_LOTTO_SIZE = "[ERROR] 현재의 로또 번호는 %d, 로또 번호는 ${LOTTO_SIZE}개여야 합니다."
    }
}
