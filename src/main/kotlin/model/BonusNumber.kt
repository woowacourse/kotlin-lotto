package model

class BonusNumber(private val lotto: Lotto, private val bonusNumber: LottoNumber) {

    init {
        require(checkDuplicateNumber()) { DUPLICATE_BONUS_NUMBER }
    }

    private fun checkDuplicateNumber(): Boolean {
        lotto.lottoNumbers.forEach { lottoNumber ->
            if (lottoNumber.number == bonusNumber.number) return false
        }
        return true
    }

    fun getBonusNumber(): Int {
        return bonusNumber.number
    }

    companion object {
        private const val DUPLICATE_BONUS_NUMBER = "[ERROR] 중복된 보너스 번호입니다"
    }
}
