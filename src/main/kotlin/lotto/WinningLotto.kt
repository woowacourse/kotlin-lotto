package lotto

class WinningLotto(
    val winningNumber: WinningNumber,
    val bonusNumber: BonusNumber,
) {
    init {
        require(winningNumber.numbers.contains(bonusNumber.number).not()) { ERROR_DUPLICATED_BONUS_NUMBER }
    }

    companion object {
        private const val ERROR_DUPLICATED_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
