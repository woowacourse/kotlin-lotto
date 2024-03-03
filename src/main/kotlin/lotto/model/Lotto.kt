package lotto.model

class Lotto(private val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == NUMBER_COUNT) { "로또 번호의 개수는 ${NUMBER_COUNT}개이어야 합니다." }
        require(numbers.toSet().size == numbers.size) { "로또 번호는 중복될 수 없습니다." }
    }

    fun checkWinningNumbers(winning: Lotto): Int = numbers.count { it in winning.numbers }

    fun checkBonusNumbers(bonusNumber: LottoNumber): Boolean = bonusNumber in numbers

    override fun toString(): String = numbers.toString()

    companion object {
        const val NUMBER_COUNT = 6
    }
}
