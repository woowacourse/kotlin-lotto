package domain.service

import domain.model.Lotto
import domain.model.LottoNumber

class ManualLottoGenerator(
    private val manualLottoInput: List<String>,
) : LottoGenerator {
    private fun makeOneLotto(lottoNumbers: Set<Int>): Lotto = Lotto(getLottoNumbers(lottoNumbers))

    override fun getLottoNumbers(lottoNumbers: Set<Int>): Set<LottoNumber> = lottoNumbers.map { LottoNumber.from(it) }.toSet()

    override fun makeLotto(amount: Int): List<Lotto> = manualLottoInput.map { makeOneLotto(it.toIntSet()) }

    companion object {
        private fun String.toIntSet() = this.split(',').map { it.toInt() }.toSet()
    }
}
