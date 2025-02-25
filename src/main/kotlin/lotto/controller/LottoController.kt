package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.Lottos
import lotto.domain.Order
import lotto.domain.Results
import lotto.domain.WinningLotto
import lotto.view.View

object LottoController {
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
        val allLottos = Lottos(manualLottos.list + automaticLottos.list)
        View.showLottos(allLottos)
        return allLottos
    }

    private fun makeLottosManually(quantity: Int): Lottos {
        View.requestManualNumbers()
        val lottoList = List(quantity) { Lotto(View.readManualNumbers().map(::LottoNumber)) }
        return Lottos(lottoList)
    }

    private fun makeLottosAutomatically(quantity: Int): Lottos {
        val lottoList = List(quantity) { Lotto(makeRandomNumbers()) }
        return Lottos(lottoList)
    }

    private fun makeRandomNumbers(): List<LottoNumber> = LottoNumber.RANGE.shuffled().subList(0, Lotto.SIZE).map(::LottoNumber)

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
        val results: Results = Results.from(winningLotto, userLottos)
        val tally = results.getTally()
        val profitRate = results.getProfitRate()
        View.showResult(tally, profitRate)
    }
}
