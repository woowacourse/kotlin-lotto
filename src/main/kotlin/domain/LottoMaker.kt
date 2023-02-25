package domain

class LottoMaker(
    private val LottoGenerator: AllTypeLottoGenerator
) {
    fun makeManualLottos(manualLottos: List<List<Int>>): Lottos {
        return Lottos(LottoGenerator.generateLottos(manualLottos))
    }

    fun makeAutoLottos(count: Int): Lottos {
        return Lottos(LottoGenerator.generateLottos(count))
    }
}
