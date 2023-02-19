package lotto

import lotto.domain.Bank
import lotto.domain.LottoBunch
import lotto.domain.LottoNumber
import lotto.domain.PurchaseMoney
import lotto.domain.WinningLotto
import lotto.domain.WinningResult
import lotto.domain.factory.LottoFactory
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(private val lottoFactory: LottoFactory) {
    fun runLotto() {
        val purchaseMoney = getPurchaseMoney()
        val lottoBunch = getLottoBunch(purchaseMoney.getPurchaseCount())
        val winningLotto = getWinningLotto()
        confirmLottoWinning(lottoBunch, winningLotto, purchaseMoney)
    }

    private fun getLottoBunch(purchaseCount: Int): LottoBunch {
        val lottoBunch = LottoBunch(lottoFactory, purchaseCount)
        OutputView.printPurchaseResult(lottoBunch, purchaseCount)
        return lottoBunch
    }

    private fun getPurchaseMoney(): PurchaseMoney {
        return runCatching {
            PurchaseMoney(InputView.getPurchaseMoney())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getPurchaseMoney) as PurchaseMoney
        }
    }

    private fun getMainLottoNumber(): List<LottoNumber> {
        return runCatching {
            InputView.getMainLottoNumber().map { number -> LottoNumber.from(number) }
        }.getOrElse { error ->
            inputErrorHandler(error, ::getMainLottoNumber) as List<LottoNumber>
        }
    }

    private fun getBonusLottoNumber(): LottoNumber {
        return runCatching {
            LottoNumber.from(InputView.getBonusLottoNumber())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getBonusLottoNumber) as LottoNumber
        }
    }

    private fun getWinningLotto(): WinningLotto {
        return runCatching {
            WinningLotto(getMainLottoNumber(), getBonusLottoNumber())
        }.getOrElse { error ->
            inputErrorHandler(error, ::getWinningLotto) as WinningLotto
        }
    }

    private fun confirmLottoWinning(lottoBunch: LottoBunch, winningLotto: WinningLotto, purchaseMoney: PurchaseMoney) {
        val ranks = lottoBunch.value.map { Bank.getRank(it, winningLotto) }
        val winningResult = WinningResult.from(ranks)
        OutputView.printWinningResult(winningResult)
        OutputView.printYieldRate(Bank.getYieldRate(purchaseMoney, Bank.sumTotalPrizeMoney(lottoBunch, winningLotto)))
    }

    private fun inputErrorHandler(
        error: Throwable,
        repeatFunction: () -> Any,
    ): Any {
        return when (error) {
            is IllegalArgumentException -> inputIllegalArgumentExceptionHandler(error, repeatFunction)
            else -> throw IllegalStateException(ERROR_INPUT_HANDLER)
        }
    }

    private fun inputIllegalArgumentExceptionHandler(
        error: Throwable,
        repeatFunction: () -> Any,
    ): Any {
        println(error.message)
        return repeatFunction()
    }

    companion object {
        private const val ERROR_INPUT_HANDLER = "값 전달 과정에 상태 오류가 발생했습니다."
    }
}
