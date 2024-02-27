package lotto.model

import model.Lotto
import model.LottoNumber

class WinningLotto(val winningNumbers: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(bonusNumber !in winningNumbers.numbers) {
            ERROR_BONUS_DUPLICATE
        }
    }

    companion object {
        const val ERROR_BONUS_DUPLICATE = "보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
