package controller

import domain.LottoStore
import domain.LottoTickets
import domain.Money
import domain.RandomLottoGenerator
import domain.WinningLotto
import view.InputView
import view.OutputView

class LottoController {
    private val inputView by lazy { InputView() }
    private val outputView by lazy { OutputView() }
    private val lottoStore by lazy { LottoStore(RandomLottoGenerator) }

    fun run() {
        val lottos = buyLotto()
        val winningLotto = makeWinningLotto()
        calculateResult(winningLotto, lottos)
    }

    private fun buyLotto(): LottoTickets {
        val money = inputView.askPurchaseMoney()
        val manualLottos = buyManualLotto(money)
        val chargeMoney = money.calculateCharge(manualLottos.size)
        val autoLottos = buyAutoLotto(chargeMoney)
        outputView.outputLottos(manualLottos, autoLottos)
        return manualLottos + autoLottos
    }

    private fun buyManualLotto(money: Money): LottoTickets =
        runCatching {
            val lottos = inputView.askManualLottoNumbers(inputView.askManualLottoCount())
            return lottoStore.buyManualLotto(money, *lottos)
        }
            .onFailure { outputView.outputError(it) }
            .getOrDefault(buyManualLotto(money))

    private fun buyAutoLotto(money: Money): LottoTickets =
        runCatching { lottoStore.buyAutoLotto(money) }
            .onFailure { outputView.outputError(it) }
            .getOrDefault(LottoTickets())

    private fun makeWinningLotto(): WinningLotto =
        runCatching { return WinningLotto(inputView.askWinningNumbers(), inputView.askBonusNumber()) }
            .onFailure { outputView.outputError(it) }
            .getOrDefault(makeWinningLotto())

    private fun calculateResult(winningLotto: WinningLotto, lottos: LottoTickets) {
        runCatching { outputView.outputResult(winningLotto.match(lottos)) }
            .onFailure { outputView.outputError(it) }
    }
}
