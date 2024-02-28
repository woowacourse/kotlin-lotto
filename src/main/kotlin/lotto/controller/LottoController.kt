package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoDrawingResult
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.Money
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun start() {
        val (lottoMachine, money) = runLottoMachine()
        makeLottoes(lottoMachine)
        val result = drawLotto(lottoMachine.lottoes)
        showResult(money, result)
    }

    private fun runLottoMachine(): Pair<LottoMachine, Money> {
        return try {
            val money: Money = getMoney()
            val manualLottoAmount = getManualLottoAmount()
            val lottoMachine = LottoMachine(money, manualLottoAmount)

            return lottoMachine to money
        } catch (e: IllegalArgumentException) {
            println(e.message)
            runLottoMachine()
        }
    }

    private fun getMoney(): Money {
        return try {
            Money(inputView.readPurchaseAmount())
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getMoney()
        }
    }

    private fun getManualLottoAmount(): Int {
        return try {
            inputView.readManualLottoAmount()
        } catch (e: IllegalArgumentException) {
            getManualLottoAmount()
        }
    }

    private fun makeLottoes(lottoMachine: LottoMachine) {
        if (lottoMachine.manual > 0) {
            println("\n수동으로 구매할 번호를 입력해 주세요.")
            repeat(lottoMachine.manual.toInt()) {
                lottoMachine.makeManualLotto(Lotto(inputView.readManualLottos()))
            }
        }
        lottoMachine.makeRandomLotto()
    }

    private fun drawLotto(lottoTickets: List<Lotto>): LottoDrawingResult {
        val winningLotto = getValidWinningLotto(getValidLotto())
        val result = winningLotto.countRank(lottoTickets)
        outputView.printLottoResult(result)
        return result
    }

    private fun getValidLotto(): Lotto {
        return try {
            Lotto(*inputView.readWinningNumbers().toIntArray())
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidLotto()
        }
    }

    private fun getValidWinningLotto(winningLotto: Lotto): WinningLotto {
        return try {
            val bonusNumber = getBonusNumber()
            WinningLotto(winningLotto, bonusNumber)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidWinningLotto(winningLotto)
        }
    }

    private fun getBonusNumber(): LottoNumber {
        return LottoNumber.from(inputView.readBonusNumber())
    }

    private fun showResult(money: Money, lottoDrawingResult: LottoDrawingResult) {
        val totalPrize = lottoDrawingResult.calculateTotalPrize()
        val marginRate = money.calculateMargin(totalPrize)
        outputView.printMargin(marginRate)
    }
}
