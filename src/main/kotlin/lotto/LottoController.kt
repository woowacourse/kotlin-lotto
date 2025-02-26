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
        // 순차적으로 실행...이게 과연 최선일까?
        val userInput = lottoView.getLottoAmount()
        val manyLotto = lottoGenerator.genManyLotto(userInput.automaticLottoCount)
        lottoView.printLotto(manyLotto)

        val userInputWinningLotto = lottoView.getWinningLotto()
        val winningLotto = Lotto(userInputWinningLotto.map { LottoNumber.of(it) })
        val bonusNum = LottoNumber.of(lottoView.getBonusNum())
        val winningLottoTicket = WinningLottoTicket(winningLotto, bonusNum)

        val rankMap = lottoRankFinder.findLottoRanks(manyLotto, winningLottoTicket)
        lottoView.printResult(rankMap)
    }
}
