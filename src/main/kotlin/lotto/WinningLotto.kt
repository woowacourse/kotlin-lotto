package lotto

class WinningLotto(
    val winningNumber: Lotto,
    val bonusNumber: LottoNumber,
) {
    init {
        require(winningNumber.numbers.contains(bonusNumber).not()) { ERROR_DUPLICATED_BONUS_NUMBER }
    }

    companion object {
        private const val ERROR_DUPLICATED_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
