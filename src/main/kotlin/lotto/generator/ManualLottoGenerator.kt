package lotto.generator

import lotto.domain.Lotto
import lotto.domain.LottoNumber

class ManualLottoGenerator(private val lottoNumbers: List<List<Int>>) : LottoGenerator {
    override fun generateLotto(): List<Lotto> {
        return lottoNumbers.map { Lotto(it.map(::LottoNumber)) }
    }
}
