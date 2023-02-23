package lotto.domain

class LottoStore(private val lottoFactory: LottoFactory) {

    fun buyLottoes(lottoCount: LottoCount, manualNumberLines: List<List<Int>> = listOf()): LottoBunch {
        return LottoBunch(
            buyManualLottoes(manualNumberLines),
            buyAutoLottoes(lottoCount.autoCount),
        )
    }

    private fun buyManualLottoes(manualNumberLines: List<List<Int>>): LottoBunch =
        LottoBunch(manualNumberLines.map { manualNumbers -> lottoFactory.createLotto(manualNumbers) })

    private fun buyAutoLottoes(lottoCount: Int): LottoBunch =
        LottoBunch(List(lottoCount) { lottoFactory.createLotto() })
}
