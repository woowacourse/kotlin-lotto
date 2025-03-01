package lotto.model

import lotto.view.InputView

class ManualLottoGenerator(private val inputView: InputView) : LottoGenerator {
    override fun generate(count: Int): List<Lotto> {
        return List(count) {
            val numbers =
                inputView.inputManualLottoNumber()
                    .map { LottoNumber.from(it) }
                    .toSet()
            Lotto(numbers)
        }
    }
}
