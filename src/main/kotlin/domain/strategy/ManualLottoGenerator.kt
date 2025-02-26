package domain.strategy

import domain.model.Lotto
import domain.model.LottoNumber

class ManualLottoGenerator(private val manualLottoNumber: List<List<LottoNumber>>) : LottoGeneratorType {
    override fun generateNumber(): List<Lotto> {
        return manualLottoNumber.map { Lotto(it) }
    }
}
