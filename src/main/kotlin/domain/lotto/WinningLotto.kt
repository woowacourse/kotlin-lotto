package domain.lotto

class WinningLotto(numbers: List<LottoNumber>, bonusNumber: LottoNumber) : Lotto(numbers) {

    init {
        validateContainedBonusNumber(bonusNumber)
    }

    private fun validateContainedBonusNumber(bonusNumber: LottoNumber) {
        check(this.none { it.value == bonusNumber.value }) { ERROR_MESSAGE_CONTAINED_BONUS_NUMBER }
    }

    companion object {
        private const val ERROR_MESSAGE_CONTAINED_BONUS_NUMBER = "[ERROR] 6개의 로또 당첨 번호에 보너스 번호가 포함될 수 없습니다."
    }
}
