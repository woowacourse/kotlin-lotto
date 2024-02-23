package lotto.model

class Lottos(
    private val lottoCount: Int,
    private var lottos: MutableList<Lotto> = mutableListOf()
) {
    init {
        makeLottos()
    }

    fun matchlottos(
        winningNumber: WinningNumber,
    ): UserPrize {
        return UserPrize(
            matches = lottos
                .groupingBy { lotto ->
                    lotto.findRanking(winningNumber)
                }
                .eachCount()
        )
    }

    fun getLottos(): List<Lotto> {
        return lottos
    }

    private fun makeLottos() {
        lottos = MutableList(lottoCount) {
            val lotto = LottoGenerator.generateLotto()
            lotto
        }
    }
}
