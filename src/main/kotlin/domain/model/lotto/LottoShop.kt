package domain.model.lotto

import domain.LottoGenerator
import domain.model.PurchaseMoney

class LottoShop(
    private val purchaseMoney: PurchaseMoney,
    val numberOfManualLottos: Int
) {

    init {
        require(numberOfManualLottos <= purchaseMoney.money / LOTTO_PRICE) {
            NUMBER_OF_MANUAL_LOTTOS_ERROR
        }
        require(numberOfManualLottos >= 0) {
            NEGATIVE_NUMBER_ERROR
        }
    }

    fun purchaseManualLotto(numbers: LottoTicket): Lotto {
        return LottoGenerator { numbers }.generateLottos().first()
    }

    fun purchaseAutoLotto(): List<Lotto> {
        return LottoGenerator(numberOfAutoLotto()).generateLottos()
    }

    fun numberOfAutoLotto(): Int = purchaseMoney.money / LOTTO_PRICE - numberOfManualLottos

    companion object {
        private const val LOTTO_PRICE = 1000
        private const val NUMBER_OF_MANUAL_LOTTOS_ERROR = "[ERROR] 구입한 로또 개수를 초과했습니다."
        private const val NEGATIVE_NUMBER_ERROR = "[ERROR] 음수일 수 없습니다."
    }
}
