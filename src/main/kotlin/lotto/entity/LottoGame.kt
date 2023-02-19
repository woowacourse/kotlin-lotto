package lotto.entity

import lotto.model.LottoGenerator

class LottoGame private constructor(val value: List<Lotto>) {
    companion object {
        fun from(lottoCount: Int, lottoGenerator: LottoGenerator): LottoGame {
            return LottoGame(
                (0 until lottoCount).map {
                    lottoGenerator.generate()
                }
            )
        }
    }
}
