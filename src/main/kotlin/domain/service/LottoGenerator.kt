package domain.service

import domain.model.Lotto
import domain.model.LottoNumber

interface LottoGenerator {
    fun getLottoNumbers(lottoNumbers: Set<Int>): Set<LottoNumber>

    fun makeLotto(amount: Int): List<Lotto>
}
