package lotto

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.UserInput
import lotto.domain.WinningLottoTicket
import lotto.global.Message
import lotto.service.LottoGenerator
import lotto.service.LottoRankFinder
import lotto.view.LottoView

class LottoController(
    private val lottoView: LottoView,
    private val lottoGenerator: LottoGenerator,
    private val lottoRankFinder: LottoRankFinder,
) {
    fun run() {
        val userInput = getUserInput()
        val autoLotto = lottoGenerator.genManyLotto(userInput.automaticLottoCount)
        val manyLotto = autoLotto + userInput.manualLotto
        lottoView.printLotto(userInput, autoLotto)

        val userInputWinningLotto = lottoView.getWinningLotto().get(this::shutdown)
        val winningLotto = Lotto.of(userInputWinningLotto)
        val bonusNum = LottoNumber.of(lottoView.getBonusNum().get(this::shutdown))
        val winningLottoTicket = WinningLottoTicket(winningLotto, bonusNum)

        val rankMap = lottoRankFinder.findLottoRanks(manyLotto, winningLottoTicket)
        lottoView.printResult(rankMap)
    }

    private fun getUserInput(): UserInput {
        val buyAmount = lottoView.getBuyAmount().get(this::shutdown)
        val manualLottoCount = lottoView.getManualLottoCount(buyAmount).get(this::shutdown)
        val manualLotto = lottoView.getManualLotto(manualLottoCount).get(this::shutdown)
        return UserInput(buyAmount, manualLottoCount, manualLotto)
    }

    private fun shutdown(err: Message) {
        lottoView.exit(err)
    }
}
