package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoCount

class ManualLottoMachine(
    private val lottoNumbers: List<List<Int>>,
) : LottoMachine {
    override fun generate(lottoCount: LottoCount): List<Lotto> = lottoNumbers.map { Lotto.of(it) } ?: emptyList()
}
