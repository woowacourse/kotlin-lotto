package lotto

fun main() {
    // 의존성 주입
    val lottoView = LottoView()
    val lottoGenerator = LottoGenerator()
    val lottoRankFinder = LottoRankFinder()
    LottoController(lottoView, lottoGenerator, lottoRankFinder).run()
}
