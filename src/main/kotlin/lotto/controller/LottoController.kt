package lotto.controller

import lotto.domain.ManualLottoGenerator
import lotto.domain.RandomLottoGenerator
import lotto.domain.model.Lotto
import lotto.domain.model.LottoDrawingResult
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTickets
import lotto.domain.model.Money
import lotto.domain.model.WinningNumbers
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val randomLottoGenerator: RandomLottoGenerator
) {

    fun start() {
        val purchaseMoney = getValidMoney()
        val totalLottoTickets = buyLottoTickets(purchaseMoney)
        val winningLotto = getValidWinningLotto()
        val winningNumbers = getValidWinningNumbers(winningLotto)
        val result = getLottoDrawingResult(winningNumbers, totalLottoTickets.tickets)
        showResult(result, purchaseMoney)
    }

    private fun getValidMoney(): Money {
        return try {
            Money(InputView.readPurchaseAmount(LOTTO_PRICE))
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidMoney()
        }
    }

    private fun buyLottoTickets(purchaseMoney: Money): LottoTickets {
        val totalLottoQuantity = getLottoQuantity(purchaseMoney)
        val manualLottoQuantity = getManualLottoQuantity(totalLottoQuantity)
        val manualLottoTickets = makeManualLottoTicket(manualLottoQuantity)
        val autoLottoTickets = makeAutoLottoTicket(totalLottoQuantity - manualLottoQuantity)
        printTotalLottoTicket(manualLottoTickets, autoLottoTickets)
        return manualLottoTickets.concat(autoLottoTickets)
    }

    private fun getLottoQuantity(money: Money): Int {
        return money.calculateQuantity(LOTTO_PRICE)
    }

    private fun getManualLottoQuantity(quantity: Int): Int {
        return try {
            InputView.readPurchaseQuantity(quantity)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getManualLottoQuantity(quantity)
        }
    }

    private fun makeManualLottoTicket(quantity: Int): LottoTickets {
        if (quantity != 0) OutputView.printInputManualNumberMessage()
        val lottoTickets = List(quantity) {
            val numbers = readValidManualLottoNumbers()
            ManualLottoGenerator(numbers).make()
        }
        return LottoTickets(lottoTickets)
    }

    private fun readValidManualLottoNumbers(): List<Int> {
        return try {
            val numbers = InputView.readLottoNumbers()
            Lotto.from(numbers)
            return numbers
        } catch (e: IllegalArgumentException) {
            println(e.message)
            readValidManualLottoNumbers()
        }
    }

    private fun makeAutoLottoTicket(quantity: Int): LottoTickets {
        val lottoTickets = List(quantity) {
            randomLottoGenerator.make()
        }
        return LottoTickets(lottoTickets)
    }

    private fun printTotalLottoTicket(
        manualLottoTickets: LottoTickets,
        autoLottoTickets: LottoTickets
    ) {
        OutputView.printLottoQuantity(manualLottoTickets.size, autoLottoTickets.size)
        OutputView.printLottoNumbers(manualLottoTickets.tickets)
        OutputView.printLottoNumbers(autoLottoTickets.tickets)
    }

    private fun getValidWinningLotto(): Lotto {
        return try {
            OutputView.printInputWinningNumberMessage()
            Lotto.from(InputView.readLottoNumbers())
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidWinningLotto()
        }
    }

    private fun getValidWinningNumbers(winningLotto: Lotto): WinningNumbers {
        return try {
            val bonusNumber = LottoNumber(InputView.readBonusNumber())
            WinningNumbers(winningLotto, bonusNumber)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidWinningNumbers(winningLotto)
        }
    }

    private fun getLottoDrawingResult(winningLotto: WinningNumbers, lottoTickets: List<Lotto>): LottoDrawingResult {
        val lottoResult = winningLotto.getResult(lottoTickets)
        OutputView.printLottoResult(lottoResult)
        return lottoResult
    }

    private fun showResult(result: LottoDrawingResult, purchaseMoney: Money) {
        val winningMoney = result.calculateTotalPrize()
        val marginRate = result.calculateEarningRate(winningMoney, purchaseMoney)
        OutputView.printMargin(marginRate)
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
