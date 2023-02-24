package model

class WinningNumber(private val lotto: Lotto, val bonusNumber: LottoNumber) {
    val winningNumbers: List<LottoNumber> = lotto.lottoNumbers.plus(bonusNumber)

    init {
        require(checkDuplicateNumber()) { DUPLICATE_BONUS_NUMBER }
    }

    private fun checkDuplicateNumber() = !lotto.lottoNumbers.contains(bonusNumber)

    companion object {
        private const val DUPLICATE_BONUS_NUMBER = "[ERROR] 중복된 보너스 번호입니다"
    }
}
