package lotto.model

import model.Lotto
import model.LottoNumber

class WinningNumbers(val winningLotto: Lotto, val bonusNumber: LottoNumber) {
    companion object {
        fun of(
            winningLottoValue: List<String>,
            bonusNumberValue: String,
        ): WinningNumbers {
            val winningLotto = Lotto(LottoNumbers(winningLottoValue.map { LottoNumber(it) }))
            val bonusNumber = LottoNumber(bonusNumberValue)
            return WinningNumbers(winningLotto, bonusNumber)
        }
    }
}
