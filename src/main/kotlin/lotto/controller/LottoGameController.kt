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
        val buyedLottos = buyLottos()
        val lottoWinningBundle = createLottoWinningBundle()
        val lottoResult = lottoWinningBundle.calculateResult(buyedLottos)
        OutputView.printResult(lottoResult)
    }

    private fun buyLottos(): List<Lotto> {
        val buyLottoPrice = InputView.reedBuyLottoPrice()
        val buyedLottos = LottoMachine.createLottos(buyLottoPrice, RandomLottoNumbersGenerator)
        OutputView.printLottos(buyedLottos)
        return buyedLottos
    }

    private fun createLottoWinningBundle(): LottoWinningBundle {
        val lottoWinningNumbers = InputView.readLottoWinningNumbers()
        val winningLotto = Lotto(lottoWinningNumbers.map { LottoNumber.of(it) })
        val lottoBonusNumber = InputView.readLottoBonusNumber()

        return LottoWinningBundle(winningLotto, LottoNumber.of(lottoBonusNumber))
    }
}
