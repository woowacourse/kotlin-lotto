package lotto

class LottoController(
    private val lottoView: LottoView,
    private val lottoService: LottoService,
) {
    fun run() {
        // 순차적으로 실행...이게 과연 최선일까?
        val lottoAmount = lottoView.getLottoAmount()
        val manyLotto = lottoService.getManyLotto(lottoAmount)
        lottoView.printLotto(manyLotto)
        val winningLotto = lottoView.getWinningLotto()
        val bonusNum = lottoView.getBonusNum()

        val rankMap = lottoService.getLottoRankMany(manyLotto, winningLotto, bonusNum)
        lottoView.printResult(rankMap)
    }
}
