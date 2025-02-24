package lotto

fun main() {
    // 의존성 주입
    val lottoView = LottoView()
    val lottoService = LottoService()
    LottoController(lottoView, lottoService).run()
}
