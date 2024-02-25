package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoWinningBundle
import lotto.model.RandomLottoNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun start() {
        val lottos = buyLottos()
        val lottoWinningBundle = createLottoWinningBundle()
        val lottoResult = lottoWinningBundle.calculateResult(lottos)
        OutputView.printResult(lottoResult)
    }

    private fun buyLottos(): List<Lotto> {
        val buyLottoPrice = InputView.reedBuyLottoPrice()
        val lottos = LottoMachine.createLottos(buyLottoPrice, RandomLottoNumbersGenerator)
        OutputView.printLottos(lottos)
        return lottos
    }

    private fun createLottoWinningBundle(): LottoWinningBundle {
        val lottoWinningNumbers = InputView.readLottoWinningNumbers()
        val winningLotto = Lotto(lottoWinningNumbers.map { LottoNumber.of(it) })
        val lottoBonusNumber = InputView.readLottoBonusNumber()

        return LottoWinningBundle(winningLotto, LottoNumber.of(lottoBonusNumber))
    }
}
