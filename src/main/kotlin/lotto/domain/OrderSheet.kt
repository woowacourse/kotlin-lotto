package lotto.domain

class OrderSheet(
    val purchaseAmount: LottoPurchaseAmount,
    val manualLottoCount: ManualLottoCount,
) {
    val autoCount: Int = purchaseAmount.purchasableCount - manualLottoCount.lottoCount
}
