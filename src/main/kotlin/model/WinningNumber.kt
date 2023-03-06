package model

class WinningNumber(val lotto: Lotto, val bonusNumber: LottoNumber) {

    init {
        require(checkDuplicateNumber()) { DUPLICATE_BONUS_NUMBER }
    }

    private fun checkDuplicateNumber() = !lotto.lottoNumbers.contains(bonusNumber)

    companion object {
        private const val DUPLICATE_BONUS_NUMBER = "[ERROR] 중복된 보너스 번호입니다"
    }
}
