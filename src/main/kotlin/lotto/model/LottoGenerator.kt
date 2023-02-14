package lotto.model

class LottoGenerator(inputMoney: Int, lottoPrice: Int, private val lottoNumberGenerator: LottoNumberGenerator) {
    val lottos: List<Lotto>

    init {
        val count = inputMoney / lottoPrice
        lottos = (0 until count).map {
            generateSingleLotto()
        }
    }

    private fun generateSingleLotto(): Lotto =
        Lotto(
            (0 until 6).map {
                lottoNumberGenerator.generate()
            }
        )
}
