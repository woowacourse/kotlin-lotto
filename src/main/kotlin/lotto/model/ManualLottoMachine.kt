package lotto.model

class ManualLottoMachine(
    private val manualLottoNumbers: List<List<Int>>,
) : LottoMachine {
    private var idx = 0

    override fun generate(): List<Int> {
        return manualLottoNumbers[idx++]
    }
}
