package domain

import common.MAXIMUM_LOTTO_RANGE
import common.MINIMUM_LOTTO_RANGE

class WinningLotto(val lotto: Lotto, bonusNumber: Int) {
    init {
        require(!lotto.numbers.contains(bonusNumber)) {
            "로또 번호와 보너스 번호는 같을 수 없습니다."
        }
        require(bonusNumber in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE) {
            "보너스 번호는 1이상 45이하여야 합니다."
        }
    }
}
