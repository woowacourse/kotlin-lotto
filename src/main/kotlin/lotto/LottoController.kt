package lotto

class LottoController(
    private val lottoView: LottoView,
    private val lottoService: LottoService,
) {
    fun run() {
        val lottoAmount = lottoView.getLottoAmount()
        val manyLotto = lottoService.getManyLotto(lottoAmount)
        lottoView.printLotto(manyLotto)
        val winningLotto = lottoView.getWinningLotto()
        val bonusNum = lottoView.getBonusNum()

        val rankMap = lottoService.checkRankMany(manyLotto, winningLotto, bonusNum)
        lottoView.printResult(rankMap)
    }
}
