package lotto.model

class Lotto private constructor(
    val numbers: List<LottoNumber>,
) {
    init {
        validateLottoNumbersCount(numbers)
        validateLottoNumbersDuplicate(numbers)
    }

    private fun validateLottoNumbersCount(numbers: List<LottoNumber>) {
        require(numbers.size == LOTTO_NUMBER_SIZE) {
            "[ERROR] 로또는 ${LOTTO_NUMBER_SIZE}개의 번호만 가질 수 있습니다."
        }
    }

    private fun validateLottoNumbersDuplicate(numbers: List<LottoNumber>) {
        require(numbers.size == numbers.toSet().size) {
            "[ERROR] 로또 번호는 중복될 수 없습니다."
        }
    }

    fun getRank(
        winningLotto: Lotto,
        bonusNumber: LottoNumber,
    ): Rank {
        validateWinningNumberAndBonusNumberDuplicate(winningLotto, bonusNumber)

        val countOfMatch = countMatchWinningNumbers(winningLotto)
        val matchBonus = isHaveBonusNumber(lotto = this, bonusNumber)

        return Rank.fromMatchResult(countOfMatch, matchBonus)
    }

    private fun validateWinningNumberAndBonusNumberDuplicate(
        winningLotto: Lotto,
        bonusNumber: LottoNumber,
    ) {
        require(!isHaveBonusNumber(winningLotto, bonusNumber)) {
            "[ERROR] 우승 번호와 보너스 번호는 중복될 수 없습니다."
        }
    }

    private fun countMatchWinningNumbers(winningNumbers: Lotto): Int =
        numbers.count { existNumber -> winningNumbers.numbers.contains(existNumber) }

    private fun isHaveBonusNumber(
        lotto: Lotto,
        bonusNumber: LottoNumber,
    ): Boolean = lotto.numbers.contains(bonusNumber)

    companion object {
        const val LOTTO_NUMBER_SIZE = 6

        fun from(numbers: List<Int>): Lotto = Lotto(numbers.map { number -> LottoNumber(number) })
    }
}
