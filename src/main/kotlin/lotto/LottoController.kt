package lotto

import lotto.domain.Lotto
import lotto.domain.LottoNumber

class LottoController(
    private val lottoView: LottoView,
    private val lottoService: LottoService,
    private val lottoGenerator: LottoGenerator,
) {
    fun run() {
        // 순차적으로 실행...이게 과연 최선일까?
        val lottoAmount = lottoView.getLottoAmount()
        val manyLotto = lottoGenerator.genManyLotto(lottoAmount)
        lottoView.printLotto(manyLotto)

        val userInputWinningLotto = lottoView.getWinningLotto()
        val winningLotto = Lotto(userInputWinningLotto.map { LottoNumber.of(it) })
        val bonusNum = lottoView.getBonusNum()

        val rankMap = lottoService.getLottoRankMany(manyLotto, winningLotto, bonusNum)
        lottoView.printResult(rankMap)
    }
}
