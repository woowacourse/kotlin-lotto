package lotto.view

class OutputView {
    fun printPurchaseLottoCount(purchaseLottoCount: Int) {
        println(PRINT_PURCHASE_LOTTO_COUNT_FORMAT.format(purchaseLottoCount))
    }

    fun printPurchaseLottoNumbers(lottoNumbers: List<Int>) {
        println(lottoNumbers)
    }

    private companion object {
        const val PRINT_PURCHASE_LOTTO_COUNT_FORMAT = "%s개 구매했습니다."
    }
}
