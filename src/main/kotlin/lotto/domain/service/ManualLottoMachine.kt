package lotto.domain.service

import lotto.domain.model.Lotto

class ManualLottoMachine : LottoMachine {
    override fun generate(lottoNumbers: List<Int>): Lotto {
        return Lotto(lottoNumbers)
    }
}
