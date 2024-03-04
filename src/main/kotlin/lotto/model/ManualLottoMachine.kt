package lotto.model

class ManualLottoMachine(
    private val manualLottoNumbers: List<List<Int>>,
) : LottoMachine {
    override fun generate(lottoSize: Int): List<Lotto> {
        return manualLottoNumbers.take(lottoSize).map { Lotto.create(it) }
    }
}
