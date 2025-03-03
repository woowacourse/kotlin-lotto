package lotto

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.UserInput
import lotto.domain.WinningLottoTicket
import lotto.global.Message
import lotto.global.UserInputResult
import lotto.view.LottoView
import kotlin.system.exitProcess

class LottoController(
    private val lottoView: LottoView,
) {
    fun run() {
        val userInput = getUserInput()
        val manyLotto = purchaseLotto(userInput)
        showPurchasedLotto(userInput, manyLotto)
        val winningLottoTicket = getWinningLottoTicket()
        showLottoResult(manyLotto, winningLottoTicket)
    }

    private fun purchaseLotto(userInput: UserInput): List<Lotto> {
        val autoLotto = Lotto.generateRandomLotto(userInput.automaticLottoCount)
        return autoLotto + userInput.manualLotto
    }

    private fun showPurchasedLotto(
        userInput: UserInput,
        manyLotto: List<Lotto>,
    ) {
        lottoView.printLotto(userInput, manyLotto)
    }

    private fun getWinningLottoTicket(): WinningLottoTicket {
        val userInputWinningLotto = lottoView.getWinningLotto()
        if (userInputWinningLotto is UserInputResult.Failure) shutdown(userInputWinningLotto.errorMessage)
        val winningLotto = Lotto.of(userInputWinningLotto.get())
        val bonusNum = lottoView.getBonusNum()
        if (bonusNum is UserInputResult.Failure) shutdown(bonusNum.errorMessage)
        return WinningLottoTicket(winningLotto, LottoNumber.of(bonusNum.get()))
    }

    private fun showLottoResult(
        manyLotto: List<Lotto>,
        winningLottoTicket: WinningLottoTicket,
    ) {
        val rankScoreBoard = winningLottoTicket.findLottoRanks(manyLotto)
        lottoView.printResult(rankScoreBoard)
    }

    private fun getUserInput(): UserInput {
        val userInputBuilder = UserInput.Builder()
        val buyAmount = lottoView.getBuyAmount()
        if (buyAmount is UserInputResult.Failure) this.shutdown(buyAmount.errorMessage)
        userInputBuilder.buyAmount(buyAmount.get()) ?: this.shutdown(Message.ERR_LESS_THAN_MINIMUM_PRICE)

        val manualLottoCount = lottoView.getManualLottoCount()
        if (manualLottoCount is UserInputResult.Failure) this.shutdown(manualLottoCount.errorMessage)
        userInputBuilder.manualLottoCount(manualLottoCount.get()) ?: this.shutdown(Message.ERR_TOO_MANY_MANUAL_LOTTO)

        val manualLotto = lottoView.getManualLotto()
        userInputBuilder.manualLotto(manualLotto.get()) ?: this.shutdown(Message.ERR_MANUAL_NOT_SUFFICIENT)
        return userInputBuilder.build()
    }

    private fun shutdown(err: Message) {
        lottoView.printMessage(err)
        exitProcess(0)
    }
}
