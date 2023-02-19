package view

import domain.LottoCustomer
import domain.model.LottoResult
import domain.model.lotto.PurchasedLottos

object ResultView {

    private const val NUMBER_OF_PURCHASED_LOTTO = "%s개를 구매했습니다."
    private const val LOTTO_RESULT_TITLE = "당첨 통계\n---------"
    private const val MATCH_RESULT = "%s개 일치 (%s원) - %s개"
    private const val PROFIT_RESULT = "총 수익률은 %s입니다."
    private const val PURCHASED_LOTTO_TYPE = "수동으로 %s장, 자동으로 %s개를 구매했습니다."

    fun printPurchasedNumberOfLottos(numberOfLotto: Int) {
        println(NUMBER_OF_PURCHASED_LOTTO.format(numberOfLotto))
    }

    fun printPurchasedLottos(lottoCustomer: LottoCustomer, purchasedLottos: PurchasedLottos) {
        println(PURCHASED_LOTTO_TYPE.format(
            lottoCustomer.manualLottosCountToPurchase, lottoCustomer.getAutomaticLottosCountToPurchase())
        )

        purchasedLottos.lottos.forEach { lotto ->
            val lottoNumbersValue = lotto.numbers.map { lottoNumber ->
                lottoNumber.value
            }
            println(lottoNumbersValue)
        }
    }

    fun printLottoResults(lottoResults: List<LottoResult>, profit: Double) {
        println(LOTTO_RESULT_TITLE)

        LottoResult.values().forEach { lottoResult ->
            println(lottoResult.formatMatchResult(lottoResults))
        }

        println(PROFIT_RESULT.format(profit))
    }

    private fun LottoResult.formatMatchResult(lottoResults: List<LottoResult>): String =
        MATCH_RESULT.format(
            this.matchCount,
            this.prizeMoney,
            lottoResults.count { lottoResult ->
                lottoResult == this
            }
        )
}
