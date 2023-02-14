package lotto.domain.numbergenerator

import lotto.constant.LOTTO_MAXIMUM_NUMBER
import lotto.constant.LOTTO_MINIMUM_NUMBER

class RandomNumberGenerator : NumberGenerator {
    override fun generate(): Int = (LOTTO_MINIMUM_NUMBER..LOTTO_MAXIMUM_NUMBER).random()
}
