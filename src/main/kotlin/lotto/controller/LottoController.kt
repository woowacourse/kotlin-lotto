package lotto.controller

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

    private fun getTotalLottoTicket(
        manualLottoTickets: LottoTickets,
        autoLottoTickets: LottoTickets
    ): LottoTickets {
        OutputView.printLottoQuantity(manualLottoTickets.size, autoLottoTickets.size)
        val totalLottoTickets = manualLottoTickets.concat(autoLottoTickets)
        OutputView.printLottoNumbers(totalLottoTickets.tickets)
        return totalLottoTickets
    }

    private fun getValidMoney(): Money {
        return try {
            Money(InputView.readPurchaseAmount(LOTTO_PRICE))
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidMoney()
        }
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

    private fun getManualLottoTicket(manualLottoQuantity: Int): LottoTickets {
        if (manualLottoQuantity != 0) OutputView.printInputManualNumberMessage()
        val manualLottoTickets = mutableListOf<Lotto>()
        repeat(manualLottoQuantity) {
            manualLottoTickets.add(getValidManualLotto())
        }
        return LottoTickets(manualLottoTickets)
    }

    private fun getValidManualLotto(): Lotto {
        return try {
            Lotto(InputView.readLottoNumbers().map { LottoNumber(it) })
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidManualLotto()
        }
    }

    private fun makeAutoLottoTicket(quantity: Int): LottoTickets {
        val lottoTickets = List(quantity) {
            randomLottoGenerator.make(LottoNumber.MINIMUM_LOTTO_NUMBER, LottoNumber.MAXIMUM_LOTTO_NUMBER)
        }
        return LottoTickets(lottoTickets)
    }

    private fun buyLottoTickets(purchaseMoney: Money): LottoTickets {
        val totalLottoQuantity = getLottoQuantity(purchaseMoney)
        val manualLottoQuantity = getManualLottoQuantity(totalLottoQuantity)
        val manualLottoTickets = getManualLottoTicket(manualLottoQuantity)
        val autoLottoTickets = makeAutoLottoTicket(totalLottoQuantity - manualLottoQuantity)
        val totalLottoTickets = getTotalLottoTicket(manualLottoTickets, autoLottoTickets)
        return totalLottoTickets
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
        val marginRate = purchaseMoney.calculateRate(result.calculateTotalPrize())
        OutputView.printMargin(marginRate)
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
