package lotto

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.WinningLottoTicket
import lotto.service.LottoGenerator
import lotto.service.LottoRankFinder
import lotto.view.LottoView

class LottoController(
    private val lottoView: LottoView,
    private val lottoGenerator: LottoGenerator,
    private val lottoRankFinder: LottoRankFinder,
) {
    fun run() {
        val userInput = lottoView.getUserInput()
        val autoLotto = lottoGenerator.genManyLotto(userInput.automaticLottoCount)
        lottoView.printLotto(autoLotto)
        val manyLotto = autoLotto + userInput.manualLotto

        val userInputWinningLotto = lottoView.getWinningLotto()
        val winningLotto = Lotto.of(userInputWinningLotto)
        val bonusNum = LottoNumber.of(lottoView.getBonusNum())
        val winningLottoTicket = WinningLottoTicket(winningLotto, bonusNum)

        val rankMap = lottoRankFinder.findLottoRanks(manyLotto, winningLottoTicket)
        lottoView.printResult(rankMap)
    }
}
