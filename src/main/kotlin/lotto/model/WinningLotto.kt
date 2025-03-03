package lotto.model

class WinningLotto(
    val winningNumbers: Lotto,
    val bonusNumber: LottoNumber,
) {
    init {
        require(
            winningNumbers.containsNumber(bonusNumber).not(),
        ) { ERROR_DUPLICATED_BONUS_NUMBER.format(bonusNumber.number) }
    }

    companion object {
        private const val ERROR_DUPLICATED_BONUS_NUMBER = "입력한 보너스 번호 %d은 당첨 번호와 중복됩니다. 보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
