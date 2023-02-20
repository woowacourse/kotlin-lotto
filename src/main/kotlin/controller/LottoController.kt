package controller

import domain.Lotto
import domain.LottoStore
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

    private fun buyLotto(): List<Lotto> {
        val money = inputView.askPurchaseMoney()
        val manualLottos = buyManualLotto(money)
        val autoLottos = buyAutoLotto(getChargeMoney(money, manualLottos))
        outputView.outputLottos(manualLottos, autoLottos)
        return manualLottos + autoLottos
    }

    private fun getChargeMoney(money: Money, manualLottos: List<Lotto>): Money =
        Money.from(money.value - lottoStore.lottoPrice * manualLottos.size)

    private fun buyManualLotto(money: Money): List<Lotto> =
        runCatching {
            val lottos = inputView.askManualLottoNumbers(inputView.askManualLottoCount())
            return lottoStore.buyManualLotto(money, *lottos)
        }
            .onFailure { outputView.outputError(it) }
            .getOrDefault(buyManualLotto(money))

    private fun buyAutoLotto(money: Money): List<Lotto> =
        runCatching { lottoStore.buyAutoLotto(money) }
            .onFailure { outputView.outputError(it) }
            .getOrDefault(emptyList())

    private fun makeWinningLotto(): WinningLotto =
        runCatching { return WinningLotto(inputView.askWinningNumbers(), inputView.askBonusNumber()) }
            .onFailure { outputView.outputError(it) }
            .getOrDefault(makeWinningLotto())

    private fun calculateResult(winningLotto: WinningLotto, lottos: List<Lotto>) {
        runCatching { outputView.outputResult(winningLotto.match(lottos)) }
            .onFailure { outputView.outputError(it) }
    }
}
