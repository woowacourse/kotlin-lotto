package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.Order
import lotto.domain.Results
import lotto.domain.WinningLotto
import lotto.view.View

object LottoController {
    fun run() {
        val order: Order = placeOrder()
        val userLottos: List<Lotto> = processOrder(order)
        val winningLotto: WinningLotto = readWinningLotto()
        showResults(winningLotto, userLottos)
    }

    private fun placeOrder(): Order {
        val payment: Int = View.readPayment()
        val manualQuantity: Int = View.readManualQuantity()
        return Order(payment, manualQuantity)
    }

    private fun processOrder(order: Order): List<Lotto> {
        val manualQuantity: Int = order.manualQuantity
        val manualLottos: List<Lotto> = makeLottosManually(manualQuantity)

        val automaticQuantity: Int = order.payment / Lotto.PRICE - manualQuantity
        val automaticLottos: List<Lotto> = makeLottosAutomatically(automaticQuantity)

        View.showLottoCount(manualQuantity, automaticQuantity)
        val allLottos: List<Lotto> = manualLottos + automaticLottos
        View.showLottos(allLottos)
        return allLottos
    }

    private fun makeLottosManually(quantity: Int): List<Lotto> {
        if (quantity > 0) View.requestManualNumbers()
        return List(quantity) { Lotto(View.readManualNumbers().map(::LottoNumber)) }
    }

    private fun makeLottosAutomatically(quantity: Int): List<Lotto> {
        return List(quantity) { Lotto(makeRandomNumbers()) }
    }

    private fun makeRandomNumbers(): List<LottoNumber> = LottoNumber.RANGE.shuffled().subList(0, Lotto.SIZE).map(::LottoNumber)

    private fun readWinningLotto(): WinningLotto {
        val lottoNumbers: List<LottoNumber> = View.readWinningNumbers().map(::LottoNumber)
        val lotto = Lotto(lottoNumbers)
        val bonusNumber = LottoNumber(View.readBonusNumber())
        return WinningLotto(lotto, bonusNumber)
    }

    private fun showResults(
        winningLotto: WinningLotto,
        userLottos: List<Lotto>,
    ) {
        val results: Results = Results.from(winningLotto, userLottos)
        View.showResult(results)
    }
}
