package lotto.controller

import lotto.domain.Cashier
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
    private val cashier: Cashier,
    private val randomLottoGenerator: RandomLottoGenerator
) {

    fun start() {
        val purchaseMoney = getValidMoney()
        val totalLottoQuantity = getLottoQuantity(purchaseMoney)
        val manualLottoQuantity = getManualLottoQuantity(totalLottoQuantity)
        val manualLottoTickets = getManualLottoTicket(manualLottoQuantity)
        val autoLottoTickets = makeAutoLottoTicket(totalLottoQuantity - manualLottoQuantity)
        val totalLottoTickets = getTotalLottoTicket(manualLottoTickets, autoLottoTickets)
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
        return cashier.calculateQuantity(money, LOTTO_PRICE)
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
        println("\n수동으로 구매할 번호를 입력해 주세요.")
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
            randomLottoGenerator.make(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
        }
        return LottoTickets(lottoTickets)
    }

    private fun getValidWinningLotto(): Lotto {
        return try {
            println("\n지난 주 당첨 번호를 입력해 주세요.")
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
        val marginRate = result.calculateMarginRate(purchaseMoney)
        OutputView.printMargin(marginRate)
    }

    companion object {
        const val LOTTO_PRICE = 1000
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
    }
}
