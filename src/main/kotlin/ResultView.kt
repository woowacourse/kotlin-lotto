object ResultView {

    private const val NUMBER_OF_PURCHASED_LOTTO = "%s개를 구매했습니다."
    fun printPurchasedNumberOfLottos(numberOfLotto: Int) {
        println(NUMBER_OF_PURCHASED_LOTTO.format(numberOfLotto))
    }

    fun printPurchasedLottos(purchasedLottos: PurchasedLottos) {
        purchasedLottos.lottos.forEach { lotto ->
            println(lotto.numbers)
        }
    }
}
