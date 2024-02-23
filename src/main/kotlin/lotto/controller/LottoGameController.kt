package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoRandomNumberGenerator
import lotto.model.LottoWinningBundle
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun start() {
        val lottos = buyLottos()
        val winningBundle = createWinningBundle()
        val lottoResult = winningBundle.calculateResult(lottos)
        OutputView.printResult(lottoResult)
    }

    private fun buyLottos(): List<Lotto> {
        val price = InputView.readPrice()
        val lottoMachine = LottoMachine(price, LottoRandomNumberGenerator())
        val lottos = lottoMachine.createLottos()
        OutputView.printLottos(lottos)
        return lottos
    }

    private fun createWinningBundle(): LottoWinningBundle {
        val winningNumbers = InputView.readWinningNumbers()
        val winningLotto = Lotto(winningNumbers.map { LottoNumber.of(it) })
        val bonusNumber = InputView.readBonusNumber()

        return LottoWinningBundle(winningLotto, LottoNumber.of(bonusNumber))
    }
}
