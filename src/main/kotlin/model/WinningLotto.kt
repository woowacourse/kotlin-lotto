package model

class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {

    init {
        require(!isBonusNumberDuplicate()) { DUPLICATE_BONUS_NUMBER }
    }

    private fun isBonusNumberDuplicate(): Boolean {
        return lotto.lottoNumbers.contains(bonusNumber)
    }

    companion object {
        private const val DUPLICATE_BONUS_NUMBER = "[ERROR] 중복된 보너스 번호입니다"
    }
}
