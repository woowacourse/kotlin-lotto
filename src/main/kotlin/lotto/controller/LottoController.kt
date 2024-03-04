package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoDrawingResult
import lotto.model.LottoMachine
import lotto.model.LottoNumber
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
        return runCatching {
            val amount = getPurchaseAmount()
            val manualLottoAmount = getManualLottoAmount()

            LottoMachine(amount, manualLottoAmount)
        }.getOrElse {
            println(it.message)
            runLottoMachine()
        }
    }

    private fun getPurchaseAmount(): Int {
        return inputView.readPurchaseAmount() ?: run {
            println(INVALID_FORMAT_EXCEPTION_MESSAGE)
            getPurchaseAmount()
        }
    }

    private fun getManualLottoAmount(): Int {
        return inputView.readManualLottoAmount() ?: run {
            println(INVALID_FORMAT_EXCEPTION_MESSAGE)
            getManualLottoAmount()
        }
    }

    private fun makeLottoes(lottoMachine: LottoMachine): List<Lotto> {
        val lottoes = makeManualLotto(lottoMachine.manual) + makeAutoLotto(lottoMachine.auto)

        outputView.printLottoNumbers(lottoMachine, lottoes)

        return lottoes
    }

    private fun makeManualLotto(countOfManual: Int): List<Lotto> {
        if (countOfManual > 0) {
            println("\n수동으로 구매할 번호를 입력해 주세요.")
        }

        return runCatching {
            List(countOfManual) {
                val manualLotto = inputView.readManualLottos().map {
                    it.trim().toIntOrNull() ?: throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE)
                }

                Lotto(manualLotto)
            }
        }.getOrElse {
            println(it.message)
            makeManualLotto(countOfManual)
        }
    }

    private fun makeAutoLotto(countOfAuto: Int): List<Lotto> {
        return runCatching {
            List(countOfAuto) { Lotto.makeRandomLotto() }
        }.getOrElse {
            println(it.message)
            makeAutoLotto(countOfAuto)
        }
    }

    private fun drawLotto(lottoTickets: List<Lotto>): LottoDrawingResult {
        val winningLotto = getValidWinningLotto()
        val lottoDrawingResult = LottoDrawingResult()

        lottoDrawingResult.countRank(lottoTickets, winningLotto)
        outputView.printLottoResult(lottoDrawingResult.statistics)

        return lottoDrawingResult
    }

    private fun getValidWinningLotto(): WinningLotto {
        return runCatching {
            val winningNumbers = getWinningNumbers()
            val bonusNumber = getBonusNumber()
            WinningLotto(winningNumbers, bonusNumber)
        }.getOrElse {
            println(it.message)
            getValidWinningLotto()
        }
    }

    private fun getWinningNumbers(): Lotto {
        return runCatching {
            val winningNumbers = inputView.readWinningNumbers().map {
                it.trim().toIntOrNull() ?: run {
                    throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION_MESSAGE)
                }
            }

            Lotto(winningNumbers)
        }.getOrElse {
            println(it.message)
            getWinningNumbers()
        }
    }

    private fun getBonusNumber(): LottoNumber {
        val bonusNumber = inputView.readBonusNumber() ?: run {
            println(INVALID_FORMAT_EXCEPTION_MESSAGE)
            return getBonusNumber()
        }

        return LottoNumber.from(bonusNumber)
    }

    private fun showResult(lottoMachine: LottoMachine, lottoDrawingResult: LottoDrawingResult) {
        val totalPrize = lottoDrawingResult.calculateTotalPrize()
        val marginRate = lottoMachine.calculateMargin(totalPrize)
        outputView.printMargin(marginRate)
    }

    companion object {
        private const val INVALID_FORMAT_EXCEPTION_MESSAGE = "입력값은 정수여야 합니다."
    }
}
