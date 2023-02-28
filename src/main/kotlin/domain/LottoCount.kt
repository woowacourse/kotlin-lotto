package domain

import util.LOTTO_PRICE
import util.PREFIX

class LottoCount(spentMoney: Money, val manualCount: Int) {
    val totalCount = (spentMoney / Money(LOTTO_PRICE)).toInt()
    val autoCount = totalCount - manualCount

    init {
        require(totalCount > 0) { "$PREFIX 구매할 수 있는 로또가 없습니다. 구매하려는 로또 개수: $totalCount" }
        require(manualCount >= 0) { "$PREFIX 수동으로 구매할 로또의 개수가 음수일 수 없습니다. 수동 로또 개수: $manualCount" }
        require(manualCount <= totalCount) { "$PREFIX 수동으로 구매할 로또의 개수가 총 로또 개수보다 많을 수 없습니다. 수동 로또 개수: $manualCount, 총 로또 개수: $totalCount" }
    }
}
