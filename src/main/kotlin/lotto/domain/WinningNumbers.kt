package lotto.domain

class WinningNumbers(val winningLotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!winningLotto.numbers.any { it.number == bonusNumber.number }) { BONUS_NUMBER_DUPLICATE_ERROR }
    }

    companion object {
        private const val BONUS_NUMBER_DUPLICATE_ERROR = "당첨번호와 보너스 번호는 중복되면 안됩니다."
    }
}