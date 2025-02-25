package domain.service

import domain.model.Lotto
import domain.model.LottoNumber

class ManualLottoGenerator : LottoNumbersGenerator {
    private var manualLottoNumbers = setOf<Int>()

    fun setManualLottoNumbers(inputLottoNumbers: Set<Int>) {
        manualLottoNumbers = inputLottoNumbers
    }

    fun makeLotto(): Lotto = Lotto(makeLottoNumbers())

    override fun makeLottoNumbers(): Set<LottoNumber> = manualLottoNumbers.map { LottoNumber(it) }.toSet()
}
