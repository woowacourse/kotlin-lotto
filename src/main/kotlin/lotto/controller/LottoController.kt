package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoDrawingResult
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.MakeManualLotto
import lotto.model.MakeRandomLotto
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun start() {
        val lottoMachine = runLottoMachine()
        val lottoes = makeLottoes(lottoMachine)
        val lottoDrawingResult = drawLotto(lottoes)
        showResult(lottoMachine, lottoDrawingResult)
    }

    private fun runLottoMachine(): LottoMachine {
        return try {
            val amount = inputView.readPurchaseAmount()
            val manualLottoAmount = getManualLottoAmount()

            return LottoMachine(amount, manualLottoAmount)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            runLottoMachine()
        }
    }

    private fun getManualLottoAmount(): Int {
        return try {
            inputView.readManualLottoAmount()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getManualLottoAmount()
        }
    }

    private fun makeLottoes(lottoMachine: LottoMachine): List<Lotto> {
        val lottoes: MutableList<Lotto> = mutableListOf()
        if (lottoMachine.manual > 0) {
            println("\n수동으로 구매할 번호를 입력해 주세요.")
        }
        repeat(lottoMachine.manual) { lottoes.add(Lotto.makeLotto(MakeManualLotto(inputView.readManualLottos()))) }
        repeat(lottoMachine.auto) { lottoes.add(Lotto.makeLotto(MakeRandomLotto())) }

        outputView.printLottoNumbers(lottoMachine, lottoes)

        return lottoes
    }

    private fun drawLotto(lottoTickets: List<Lotto>): LottoDrawingResult {
        val winningLotto = getValidWinningLotto(getValidLotto())
        val lottoDrawingResult = LottoDrawingResult()

        lottoDrawingResult.countRank(lottoTickets, winningLotto)
        outputView.printLottoResult(lottoDrawingResult.statistics)

        return lottoDrawingResult
    }

    private fun getValidLotto(): Lotto {
        return try {
            Lotto(inputView.readWinningNumbers())
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

    private fun getBonusNumber(): LottoNumber = LottoNumber.from(inputView.readBonusNumber())

    private fun showResult(lottoMachine: LottoMachine, lottoDrawingResult: LottoDrawingResult) {
        val totalPrize = lottoDrawingResult.calculateTotalPrize()
        val marginRate = lottoMachine.calculateMargin(totalPrize)
        outputView.printMargin(marginRate)
    }
}
