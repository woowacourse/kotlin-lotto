package lotto

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.UserInput
import lotto.domain.WinningLottoTicket
import lotto.global.Message
import lotto.global.UserInputResult
import lotto.service.LottoGenerator
import lotto.view.LottoView
import kotlin.system.exitProcess

class LottoController(
    private val lottoView: LottoView,
    private val lottoGenerator: LottoGenerator,
) {
    fun run() {
        val userInput = getUserInput()
        val manyLotto = purchaseLotto(userInput)
        showPurchasedLotto(userInput, manyLotto)
        val winningLottoTicket = getWinningLottoTicket()
        showLottoResult(manyLotto, winningLottoTicket)
    }

    private fun purchaseLotto(userInput: UserInput): List<Lotto> {
        val autoLotto = lottoGenerator.makeLotto(userInput.automaticLottoCount)
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
        val rankMap = winningLottoTicket.findLottoRanks(manyLotto)
        lottoView.printResult(rankMap)
    }

    private fun getUserInput(): UserInput {
        val buyAmount = lottoView.getBuyAmount()
        if (buyAmount is UserInputResult.Failure) this.shutdown(buyAmount.errorMessage)
        val manualLottoCount = lottoView.getManualLottoCount(buyAmount.get())
        if (manualLottoCount is UserInputResult.Failure) this.shutdown(manualLottoCount.errorMessage)
        val manualLotto = lottoView.getManualLotto(manualLottoCount.get())
        return UserInput(buyAmount.get(), manualLottoCount.get(), manualLotto.get())
    }

    private fun shutdown(err: Message) {
        lottoView.printMessage(err)
        exitProcess(0)
    }
}
