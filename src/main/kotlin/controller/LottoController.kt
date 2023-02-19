package controller

import domain.Lotto
import domain.LottoNumber
import domain.LottoStore
import domain.Money
import domain.RandomLottoGenerator
import domain.WinningLotto
import view.InputView
import view.OutputView

class LottoController {
    private val inputView by lazy { InputView() }
    private val outputView by lazy { OutputView() }

    fun run() {
        val money = askPurchaseMoney()
        val lottos = buyLotto(money)
        outputView.outputLottos(lottos)
        val winningLotto = WinningLotto(askWinningNumbers(), askBonusNumber())
        val result = winningLotto.match(lottos)
        outputView.outputResult(result)
    }

    private fun askPurchaseMoney(): Money {
        outputView.outputGetAmount()
        return Money.from(inputView.inputAmount())
    }

    private fun buyLotto(money: Money): List<Lotto> {
        val store = LottoStore(RandomLottoGenerator)
        return store.buyLotto(money)
    }

    private fun askWinningNumbers(): Lotto {
        outputView.outputGetWinningNumbers()
        return Lotto(*inputView.inputWinningNumbers())
    }

    private fun askBonusNumber(): LottoNumber {
        outputView.outputGetBonusNumber()
        return LottoNumber.from(inputView.inputBonusNumber())
    }
}
