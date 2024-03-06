package lotto.model

import lotto.exception.Exceptions

class ManualLottoPurchaseCount private constructor(val count: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ManualLottoPurchaseCount) return false

        return count == other.count
    }

    override fun hashCode(): Int {
        return count.hashCode()
    }

    companion object {
        private const val MIN_MANUAL_LOTTO_COUNT = 0

        fun from(
            count: String,
            numberOfLotto: Int,
        ): ManualLottoPurchaseCount {
            if (count.toInt() < MIN_MANUAL_LOTTO_COUNT) {
                throw Exceptions.PurchaseSizeOfManualLottoException()
            }
            if (count.toInt() > numberOfLotto) {
                throw Exceptions.PurchaseSizeOfManualLottoAmountException()
            }

            return ManualLottoPurchaseCount(count.toInt())
        }
    }
}
