package domain

class LottoMaker(
    private val lottoGenerator: AllTypeLottoGenerator
) {
    fun makeManualLottos(manualLottos: List<List<Int>>): Lottos {
        return Lottos(lottoGenerator.generateLottos(manualLottos))
    }

    fun makeAutoLottos(count: Int): Lottos {
        return Lottos(lottoGenerator.generateLottos(count))
    }
}
