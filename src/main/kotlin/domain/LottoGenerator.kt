package domain

interface LottoGenerator {
    fun generateLottos(money: Money): Lottos
}
