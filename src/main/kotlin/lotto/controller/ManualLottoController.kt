package lotto.controller

import lotto.entity.Lotto
import lotto.misc.tryAndRerun
import lotto.view.InputView

class ManualLottoController(private val inputView: InputView) {
    fun readLotto(): Lotto {
        return tryAndRerun {
            inputView.readLotto()
        }
    }
}
