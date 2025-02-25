package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.LottoResults
import lotto.domain.Lottos
import lotto.domain.Order
import lotto.domain.WinningLotto
import lotto.view.View

class LottoController {
    fun run() {
        val order: Order = placeOrder()
        val userLottos: Lottos = makeLottos(order)
        val winningLotto: WinningLotto = readWinningLotto()
        showResult(winningLotto, userLottos)
    }

    private fun placeOrder(): Order {
        val payment: Int = View.readPayment()
        val manualQuantity: Int = View.readManualQuantity()
        return Order(payment, manualQuantity)
    }

    private fun makeLottos(order: Order): Lottos {
        val totalQuantity: Int = order.payment / Lotto.PRICE
        val manualQuantity: Int = order.manualQuantity
        val automaticQuantity: Int = totalQuantity - manualQuantity
        val manualLottos: Lottos = makeLottosManually(manualQuantity)
        val automaticLottos: Lottos = makeLottosAutomatically(automaticQuantity)

        View.showLottoCount(manualQuantity, automaticQuantity)
        val allLottos = Lottos(manualLottos.value + automaticLottos.value)
        View.showLottos(allLottos)
        return allLottos
    }

    private fun makeLottosManually(quantity: Int): Lottos {
        View.requestManualNumbers()
        val lottosList = List(quantity) { Lotto(View.readManualNumbers().map(::LottoNumber)) }
        return Lottos(lottosList)
    }

    private fun makeLottosAutomatically(quantity: Int): Lottos {
        val lottosList = List(quantity) { Lotto(makeLottoNumbers()) }
        return Lottos(lottosList)
    }

    private fun makeLottoNumbers(): List<LottoNumber> = LottoNumber.RANGE.shuffled().subList(0, Lotto.SIZE).map(::LottoNumber)

    private fun readWinningLotto(): WinningLotto {
        val lottoNumbers: List<LottoNumber> = View.readWinningNumbers().map(::LottoNumber)
        val lotto = Lotto(lottoNumbers)
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
