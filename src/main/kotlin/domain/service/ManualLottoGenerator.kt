package domain.service

import domain.model.Lotto
import domain.model.LottoNumber

class ManualLottoGenerator(
    private val manualLottoInput: List<String>,
) : LottoGenerator {
    private val lottoNumbers: ArrayDeque<List<Int>> = ArrayDeque(manualLottoInput.map { it.toIntList() })

    override fun getLottoNumbers(): Set<LottoNumber> = lottoNumbers.removeFirst().toLottoNumberSet()

    override fun makeLotto(amount: Int): List<Lotto> = List(amount) { Lotto.from(getLottoNumbers()) }

    companion object {
        private fun String.toIntList() = this.split(',').map { it.toInt() }

        fun List<Int>.toLottoNumberSet() = this.map { LottoNumber.from(it) }.toSet()
    }
}
