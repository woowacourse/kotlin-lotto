package domain

interface LottoGenerator {
    fun generateLottos(money: Int): Lottos
}
