package domain.service

import domain.model.Lotto
import domain.model.LottoNumber

class AutoLottoGenerator : LottoNumbersGenerator {
    fun makeLotto(): Lotto = Lotto(makeLottoNumbers())

    override fun makeLottoNumbers(): Set<LottoNumber> =
        (LOTTO_MIN..LOTTO_MAX)
            .shuffled()
            .take(6)
            .map { LottoNumber.from(it) }
            .toSet()

    companion object {
        const val LOTTO_MIN = 1
        const val LOTTO_MAX = 45
    }
}
