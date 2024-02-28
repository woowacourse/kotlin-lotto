package lotto.model

class Lottos {
    private var lottos: List<Lotto> = listOf()

    fun publishLottos(
        numberOfLotto: Int,
        handpickedLottos: List<Lotto>,
    ) {
        val automaticLottos = List(numberOfLotto - handpickedLottos.size) { LottoGenerator.generateLotto() }
        lottos = (handpickedLottos + automaticLottos)
    }

    fun getLottos(): List<Lotto> = lottos

    override fun toString(): String {
        return lottos.joinToString("\n")
    }
}
