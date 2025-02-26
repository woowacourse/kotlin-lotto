package domain.service

import domain.model.Lotto
import domain.model.LottoNumber

class ManualLottoGenerator(
    private val manualLottoInput: List<String>,
) : LottoGenerator {
    override fun getLottoNumbers(lottoNumbers: Set<Int>): Set<LottoNumber> = lottoNumbers.map { LottoNumber.from(it) }.toSet()

    override fun makeLotto(amount: Int): List<Lotto> = manualLottoInput.map { Lotto(getLottoNumbers(it.toIntSet())) }

    companion object {
        private fun String.toIntSet() = this.split(',').map { it.toInt() }.toSet()
    }
}
