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
        View.requestManualNumbers()
        val manualLottos: List<Lotto> = List(order.manualQuantity) { readManualNumbers() }.map(::Lotto)
        val automaticLottos: List<Lotto> = List(order.automaticQuantity) { makeRandomNumbers() }.map(::Lotto)
        showPurchaseInformation(manualLottos, automaticLottos)
        val allLottos: List<Lotto> = manualLottos + automaticLottos
        return allLottos
    }

    private fun showPurchaseInformation(
        manualLottos: List<Lotto>,
        automaticLottos: List<Lotto>,
    ) {
        View.showLottoCount(manualLottos.size, automaticLottos.size)
        View.showLottos(manualLottos, automaticLottos)
    }

    private fun readManualNumbers(): List<LottoNumber> = View.readManualNumbers().map(::LottoNumber)

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
