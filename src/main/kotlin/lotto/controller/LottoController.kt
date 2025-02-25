package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoManager
import lotto.domain.LottoNumber
import lotto.domain.LottoResults
import lotto.domain.Lottos
import lotto.domain.WinningLotto
import lotto.view.View

class LottoController {
    fun run() {
        val userLottos: Lottos = buyLottos()
        val winningLotto: WinningLotto = readWinningLotto()
        showResult(winningLotto, userLottos)
    }

    private fun buyLottos(): Lottos {
        val payment: Int = View.readPayment()
        val totalQuantity: Int = payment / Lotto.PRICE
        val manualQuantity: Int = View.readManualQuantity()
        val automaticQuantity: Int = totalQuantity - manualQuantity

        val lottoManager = LottoManager(manualQuantity, automaticQuantity)
        val manualLottos: Lottos = buyLottosManually(lottoManager)
        val automaticLottos: Lottos = buyLottosAutomatically(lottoManager)

        View.showLottoCount(manualQuantity, automaticQuantity)
        val allLottos = Lottos(manualLottos.value + automaticLottos.value)
        View.showLottos(allLottos)
        return allLottos
    }

    private fun buyLottosManually(lottoManager: LottoManager): Lottos {
        View.requestManualNumbers()
        val lottosList = List(lottoManager.manualQuantity) { Lotto(View.readManualNumbers().map(::LottoNumber).toSet()) }
        return Lottos(lottosList)
    }

    private fun buyLottosAutomatically(lottoManager: LottoManager): Lottos {
        val lottosList = List(lottoManager.automaticQuantity) { Lotto(makeLottoNumbers()) }
        return Lottos(lottosList)
    }

    private fun makeLottoNumbers(): Set<LottoNumber> = LottoNumber.RANGE.shuffled().subList(0, Lotto.SIZE).map(::LottoNumber).toSet()

    private fun readWinningLotto(): WinningLotto {
        val lottoNumbers: List<LottoNumber> = View.readWinningNumbers().map(::LottoNumber)
        val lotto = Lotto(lottoNumbers.toSet())
        val bonusNumber = LottoNumber(View.readBonusNumber())
        return WinningLotto(lotto, bonusNumber)
    }

    private fun showResult(
        winningLotto: WinningLotto,
        userLottos: Lottos,
    ) {
        val lottoResults: LottoResults = LottoResults.from(winningLotto, userLottos)
        val tally = lottoResults.getTally()
        val profitRate = lottoResults.getProfitRate()
        View.showResult(tally, profitRate)
    }
}
