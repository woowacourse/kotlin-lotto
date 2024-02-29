package lotto.model


class Lottos {
    private var lottos: List<Lotto> = listOf()

    fun matchLottos(
        winningNumber: WinningNumber,
    ): UserPrize {
        return UserPrize(
            matchResult = lottos
                .groupingBy { lotto ->
                    lotto.findRanking(winningNumber)
                }
                .eachCount()
        )
    }

    fun getLottos(): List<Lotto> {
        return lottos
    }

    fun purchaseLottos(purchasedLottos: List<Lotto>) {
        lottos = lottos.plus(purchasedLottos)
    }
}
