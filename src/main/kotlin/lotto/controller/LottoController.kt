package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.Order
import lotto.domain.Order.Payment
import lotto.domain.Order.Quantity
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
        val payment = Payment(View.readPayment())
        val manualQuantity = Quantity(View.readManualQuantity())
        View.requestManualNumbers()
        val manualNumbersList: List<List<Int>> = List(manualQuantity.value) { readManualNumbers() }
        return Order(payment, manualQuantity, manualNumbersList)
    }

    private fun readManualNumbers(): List<Int> = View.readManualNumbers()

    private fun processOrder(order: Order): List<Lotto> {
        val manualLottos: List<Lotto> = order.manualNumbersList.map(::Lotto)
        val automaticLottos: List<Lotto> = List(order.automaticQuantity.value) { makeRandomNumbers() }.map(::Lotto)
        showPurchaseInformation(manualLottos, automaticLottos)
        val allLottos: List<Lotto> = manualLottos + automaticLottos
        return allLottos
    }

    private fun makeRandomNumbers(): List<LottoNumber> = LottoNumber.RANGE.shuffled().subList(0, Lotto.SIZE).map(LottoNumber::from)

    private fun showPurchaseInformation(
        manualLottos: List<Lotto>,
        automaticLottos: List<Lotto>,
    ) {
        View.showLottoCount(manualLottos.size, automaticLottos.size)
        View.showLottos(manualLottos, automaticLottos)
    }

    private fun readWinningLotto(): WinningLotto {
        val lottoNumbers: List<LottoNumber> = View.readWinningNumbers().map(LottoNumber::from)
        val lotto = Lotto(lottoNumbers)
        val bonusNumber = LottoNumber.from(View.readBonusNumber())
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
