package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.value.LottoCount
import lotto.domain.value.LottoNumber

class ManualLottoMachine : LottoMachine {
    override fun generate(
        lottoCount: LottoCount,
        lottoNumbers: List<List<Int>>?,
    ): List<Lotto> =
        lottoNumbers?.map { numbers ->
            Lotto(numbers.map { LottoNumber.from(it) })
        } ?: throw IllegalArgumentException()
}
