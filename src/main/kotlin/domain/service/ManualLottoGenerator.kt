package domain.service

import domain.model.Lotto
import domain.model.LottoNumber

class ManualLottoGenerator(
    private val manualLottoNumbers: Set<Int>,
) : LottoNumbersGenerator {
    fun makeLotto(): Lotto = Lotto(makeLottoNumbers())

    override fun makeLottoNumbers(): Set<LottoNumber> = manualLottoNumbers.map { LottoNumber(it) }.toSet()
}
