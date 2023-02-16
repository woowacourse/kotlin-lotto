package model

class BonusNumber(val lotto: Lotto, val bonusNumber: LottoNumber) {

    init {
        require(checkDuplicateNumber()) { DUPLICATE_BONUS_NUMBER }
    }

    private fun checkDuplicateNumber(): Boolean {
        lotto.lottoNumbers.forEach { lottoNumber ->
            if (lottoNumber.number == bonusNumber.number) return false
        }
        return true
    }

    companion object {
        private const val DUPLICATE_BONUS_NUMBER = "[ERROR] 중복된 보너스 번호입니다"
    }
}
