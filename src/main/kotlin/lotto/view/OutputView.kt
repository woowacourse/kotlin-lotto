package lotto.view

import lotto.constants.StringConstants
import lotto.model.LottoStore

object OutputView {

    fun printPurchaseLotto(lottoStore: LottoStore) {
        println(StringConstants.OUTPUT_PURCHASE_COUNT.format(lottoStore.lottos.size))
        lottoStore.lottos.forEach { lotto ->
            println(lotto)
        }
        println()
    }

}
